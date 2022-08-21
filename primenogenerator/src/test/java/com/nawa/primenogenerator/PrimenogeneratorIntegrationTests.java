package com.nawa.primenogenerator;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.nawa.primenogenerator.controller.PrimeNoController;

import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;

@SpringBootTest
@AutoConfigureMockMvc
class PrimenogeneratorIntegrationTests {

	@Autowired
	private PrimeNoController primeNoController;

	@Autowired
	private MockMvc mockMvc;

	/**
	 * This does an smoke test to check if the Controller is being loaded or not
	 */
	@Test
	void contextLoads() {
		assertNotNull(primeNoController);
	}

	/**
	 * This is an integration test where the API is being mocked and we check if the
	 * status returned is Okay HTTP status Code 200 and the Result String contains
	 * the uniqueResultId
	 * 
	 * @throws Exception
	 */
	@Test
	public void getPrimeNosAlgoInterative() throws Exception {
		this.mockMvc.perform(get("/api/v1/primenos/algorithmName/ITERATIVE/upperLimit/3")).andDo(print())
				.andExpect(status().isOk()).andExpect(content().string(containsString("unqiueResultId")));
	}

	/**
	 * This is an integration test where the API is being mocked and we check if the
	 * status returned is Okay HTTP status Code 200 and the Result String contains
	 * the uniqueResultId
	 * 
	 * @throws Exception
	 */
	@Test
	public void getPrimeNosAlgoParallelStream() throws Exception {
		this.mockMvc.perform(get("/api/v1/primenos/algorithmName/PARALLELSTREAM/upperLimit/11")).andDo(print())
				.andExpect(status().isOk()).andExpect(jsonPath("$.primeNos").value(Matchers.contains(2,3,5,7,11)));
	}

	/**
	 * This is an integration test where the API is being mocked and we check if the
	 * status returned is Okay HTTP status Code 200 and the Result String contains
	 * the uniqueResultId
	 * 
	 * @throws Exception
	 */
	@Test
	public void getPrimeNosDefaultAlgo() throws Exception {
		this.mockMvc.perform(get("/api/v1/primenos/upperLimit/3")).andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.primeNos").value(Matchers.contains(2,3)));
	}

	@Test
	public void getPrimeResultByResultId() throws Exception {
		MvcResult mvcResult = this.mockMvc.perform(get("/api/v1/primenos/algorithmName/PARALLELSTREAM/upperLimit/3"))
				.andReturn();
		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(mvcResult.getResponse().getContentAsString());
		String resultId = (String) json.get("unqiueResultId");
		this.mockMvc.perform(get("/api/v1/primenos/" + resultId)).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString(resultId)));

	}

	@Test
	public void getNoneResultByResultId() throws Exception {
		this.mockMvc.perform(get("/api/v1/primenos/dummy")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("Result not found for the UUID: dummy")));
	}

	@Test
	public void getAlgos() throws Exception {
		this.mockMvc.perform(get("/api/v1/primenos/algorithms")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("PARALLELSTREAM")));
	}

}
