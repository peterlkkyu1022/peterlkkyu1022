package com.app.demo.service;

import com.app.demo.entity.CurrencyModel;
import com.app.demo.model.*;
import com.app.demo.repository.CurrencyModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional
public class ApiService {

    @Value( "${condesk.url}" )
    private String condeskUrl;

    @Autowired
    private CurrencyModelRepository currencyModelRepository;

    public Coindesk coindestTestAPI(){
        RestTemplate restTemplate = new RestTemplate();


        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        messageConverters.add(converter);
        restTemplate.setMessageConverters(messageConverters);

        Coindesk result = restTemplate.getForObject(condeskUrl, Coindesk.class);

//        Map map = JSON.parseObject(result.getBpi().toString());

        return result;

    }

    public CoindeskInfo getCoinDeskInfo(){
        RestTemplate restTemplate = new RestTemplate();

        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        messageConverters.add(converter);
        restTemplate.setMessageConverters(messageConverters);

        Coindesk result = restTemplate.getForObject(condeskUrl, Coindesk.class);


        Date date=null;
        String DateString = "";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        DateFormat formatter1 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        try {
            date = formatter.parse(result.getTime().getUpdatedISO().substring(0, 22));
            DateString = formatter1.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        CoindeskInfo coindeskInfo = new CoindeskInfo();
        coindeskInfo.setUpdateTime(DateString);
        List<CoindeskInfoItem> coindeskInfoItems = new ArrayList<>();

        result.getBpi().forEach((u, v) -> {
            CoindeskInfoItem coindeskInfoItem = new CoindeskInfoItem();
            coindeskInfoItem.setCur(v.getCode());
            List<CurrencyModel> currencyModels = currencyModelRepository.findByCur(v.getCode());
            coindeskInfoItem.setCurName((currencyModels.size() > 0) ? currencyModels.get(0).getCurdesc() : "No Chinese");
            coindeskInfoItem.setExchangerate((currencyModels.size() > 0) ? currencyModels.get(0).getExchangerate().toString() : "");
            coindeskInfoItems.add(coindeskInfoItem);
        });

        coindeskInfo.setItems(coindeskInfoItems);

        return coindeskInfo;
    }


}
