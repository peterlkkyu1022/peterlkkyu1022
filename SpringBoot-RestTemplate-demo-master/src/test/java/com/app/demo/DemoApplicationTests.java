package com.app.demo;

import com.alibaba.fastjson.JSON;
import com.app.demo.controller.ApiController;
import com.app.demo.entity.CurrencyModel;
import com.app.demo.repository.CurrencyModelRepository;
import com.app.demo.service.ApiService;
import com.app.demo.service.CurrencyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
@AutoConfigureMockMvc
public class DemoApplicationTests {
    // we inject the server side Spring MVC test support
    @Autowired
    private MockMvc mockMvc;

//    @MockBean
//    private ApiService apiService;
//
//    @MockBean
//    private CurrencyService currencyService;
//
//    @MockBean
//    CurrencyModelRepository currencyModelRepository;

    /**
     * 測試呼叫coindesk API，並顯示其內容。
     * @throws Exception
     */
    @Test
    public void getCoinDeskTestData() throws Exception
    {
        ResultActions perform = mockMvc.perform(MockMvcRequestBuilders.get("/api/getTestData"));
        // 列印下結果看看
        System.out.println("========>" + perform.andReturn().getResponse().getContentAsString());
        perform.andExpect(MockMvcResultMatchers.status().isOk());

    }

    /**
     * 測試呼叫資料轉換的API，並顯示其內容。
     * @throws Exception
     */
    @Test
    public void getCoinDeskData() throws Exception
    {
        String response = mockMvc.perform(get("/api/coindesk")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();

    }

    /**
     * 測試呼叫查詢幣別對應表資料API，並顯示其內容
     * @throws Exception
     */
    @Test
    public void getCurrencyQuery() throws Exception
    {
        String response = mockMvc.perform(get("/api/query")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();

    }

    /**
     * 測試呼叫新增幣別對應表資料API
     * @throws Exception
     */
    @Test
    public void getCurrencyInsert() throws Exception{
        CurrencyModel currencyModel = new CurrencyModel();
        currencyModel.setCur("TWD");
        currencyModel.setCurdesc("台幣");
        currencyModel.setExchangerate(BigDecimal.valueOf(1));
        String jsonData = JSON.toJSONString(currencyModel);

        String response = mockMvc.perform(post("/api/insert")
                .content(jsonData).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn().getResponse().getContentAsString();

    }

    /**
     * 測試呼叫更新幣別對應表資料API，並顯示其內容。
     * @throws Exception
     */
    @Test
    public void getCurrencyUpdate() throws Exception{
        CurrencyModel currencyModel = new CurrencyModel();
        currencyModel.setCur("TWD");
        currencyModel.setCurdesc("台幣");
        currencyModel.setExchangerate(BigDecimal.valueOf(1));
        String jsonData = JSON.toJSONString(currencyModel);

        String response = mockMvc.perform(put("/api/update/3")
                        .content(jsonData).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();

    }

    /**
     * 測試呼叫刪除幣別對應表資料API。
     * @throws Exception
     */
    @Test
    public void getCurrencyDelete() throws Exception{
        this.mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/delete/{id}", "3")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andReturn().getResponse().getContentAsString();

    }


}
