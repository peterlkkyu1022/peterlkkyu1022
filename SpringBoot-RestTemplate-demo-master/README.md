**請撰寫以下各項功能之單元測試：**

>1. 測試呼叫查詢幣別對應表資料API，並顯示其內容。
>2. 測試呼叫新增幣別對應表資料API。
>3. 測試呼叫更新幣別對應表資料API，並顯示其內容。
>4. 測試呼叫刪除幣別對應表資料API。
>5. 測試呼叫coindesk API，並顯示其內容。
>6. 測試呼叫資料轉換的API，並顯示其內容。

>使用 DemoApplicationTests.java 做單元測試

**H2 SQL 資料**
```
1.data.sql
2.schema.sql
```

**API 資訊**
```
1. Get    http://localhost:8088/api/getTestData 呼叫coindesk API
2. Get    http://localhost:8088/api/coindesk 呼叫資料轉換的API
3. Get    http://localhost:8088/api/query 查詢幣別對應表資料API
4. Post   http://localhost:8088/api/insert 新增幣別對應表資料API
   Json : 
   {
        "cur": "TWD",
        "curdesc": "台幣",
        "exchangerate": 40
   }
5. Put    http://localhost:8088/api/update/{id} 更新幣別對應表資料API
   Json : 
   {
        "cur": "USD",
        "curdesc": "美金",
        "exchangerate": 40
   }
6. Delete http://localhost:8088/api/delete/{id} 刪除幣別對應表資料API
```