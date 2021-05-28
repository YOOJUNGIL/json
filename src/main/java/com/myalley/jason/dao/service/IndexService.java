package com.myalley.jason.dao.service;

import com.google.gson.Gson;
import com.myalley.jason.dao.dto.ResultDto;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class IndexService {

    private String jsonStr = "{ 'errCode' : '000', 'errMsg' : '정상처리되었습니다' }";
    private String jsonStr2 = "{ \"errCode\" : \"000\", \"errMsg\" : \"정상처리되었습니다\" }";
    private String jsonStr3 = "{ 'errCode' : '000', 'errMsg' : '정상처리되었습니다' , 'data' : [{'name' : 'yoojungil' }, { 'name' : 'xcon9999' }, { 'name' : 'jungilyoo'}]}";

    /**
     * ' 홀따옴표, Array없음
     * @return
     * @throws Exception
     */
    public String json1() throws Exception {
        net.minidev.json.parser.JSONParser parser = new net.minidev.json.parser.JSONParser();
        net.minidev.json.JSONObject jsonObject = (net.minidev.json.JSONObject) parser.parse(jsonStr);
        jsonObject.put("errStatus", "complete");
        if(log.isDebugEnabled()) {
            log.debug("errCode : " + jsonObject.getAsString("errCode"));
            log.debug("jonObject : " + jsonObject);
        }
        return jsonObject.getAsString("errCode");
    }

    /**
     * " 쌍따옴표, Array없음
     * @return
     * @throws Exception
     */
    public String json2() throws Exception {
        org.json.simple.parser.JSONParser jsonParse = new org.json.simple.parser.JSONParser();
        org.json.simple.JSONObject jsonObject = (org.json.simple.JSONObject) jsonParse.parse(jsonStr2);
        jsonObject.put("errStatus", "complete");
        if(log.isDebugEnabled()) {
            log.debug("errMsg : " + jsonObject.get("errMsg"));
            log.debug("jonObject : " + jsonObject);
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
        if(log.isDebugEnabled()) {
            log.debug("gson.fromJson => resultDto : " + resultDto);
        }
        String jsonStr = gson.toJson(resultDto);
        if(log.isDebugEnabled()) {
            log.debug("gson.toJson => jsonStr : " + jsonStr);
        }
        return resultDto.getErrCode() + ":" + resultDto.getErrMsg();
    }

    /**
     * Array있음
     * @return
     * @throws Exception
     */
    public String json4() throws Exception {
        org.json.JSONObject jsonObject = new org.json.JSONObject(jsonStr);
        jsonObject.put("errStatus", "complete");
        if(log.isDebugEnabled()) {
            log.debug("jsonObject : " + jsonObject);
        }

        org.json.JSONObject jsonObject2 = new org.json.JSONObject(jsonStr2);
        if(log.isDebugEnabled()) {
            log.debug("jsonObject2 : " + jsonObject2);
        }

        org.json.JSONObject jsonObject3 = new org.json.JSONObject(jsonStr3);
        org.json.JSONArray jsonArray = jsonObject3.getJSONArray("data");
        if(log.isDebugEnabled()) {
            log.debug("length : " + jsonArray.length());
            log.debug("jsonArray : " + jsonArray);
        }
        for(Object each : jsonArray) {
            org.json.JSONObject jobject = (org.json.JSONObject) each;
            if(log.isDebugEnabled()) {
                log.debug("each : " + jobject.get("name"));
            }
        }
        return jsonObject.getString("errCode");
    }

}
