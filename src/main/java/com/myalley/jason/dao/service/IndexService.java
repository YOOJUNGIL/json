package com.myalley.jason.dao.service;

import com.google.gson.Gson;
import com.myalley.jason.dao.dto.ResultDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class IndexService {

    private String jsonStr = "{ 'errCode' : '000', 'errMsg' : '정상처리되었습니다' }";
    private String jsonStr2 = "{ \"errCode\" : \"000\", \"errMsg\" : \"정상처리되었습니다\" }";

    /**
     * ' 홀따옴표
     * @return
     * @throws Exception
     */
    public String json1() throws Exception {
        net.minidev.json.parser.JSONParser parser = new net.minidev.json.parser.JSONParser();
        net.minidev.json.JSONObject jsonObject = (net.minidev.json.JSONObject) parser.parse(jsonStr);
        if(log.isDebugEnabled()) {
            log.debug("errCode : " + jsonObject.getAsString("errCode"));
        }
        return jsonObject.getAsString("errCode");
    }

    /**
     * " 쌍따옴표
     * @return
     * @throws Exception
     */
    public String json2() throws Exception {
        org.json.simple.parser.JSONParser jsonParse = new org.json.simple.parser.JSONParser();
        org.json.simple.JSONObject jsonObject = (org.json.simple.JSONObject) jsonParse.parse(jsonStr2);
        if(log.isDebugEnabled()) {
            log.debug("errMsg : " + jsonObject.get("errMsg"));
        }
        return (String) jsonObject.get("errMsg");
    }

    /**
     * 무관
     * @return
     * @throws Exception
     */
    public String json3() throws Exception {
        Gson gson = new Gson();
        ResultDto resultDto = gson.fromJson(jsonStr, ResultDto.class);
        return resultDto.getErrCode() + ":" + resultDto.getErrMsg();
    }

}
