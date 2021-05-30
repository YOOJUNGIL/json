package com.myalley.jason.dao.service;

import com.google.gson.*;
import com.myalley.jason.dao.dto.FamilyDetail;
import com.myalley.jason.dao.dto.FamilyMst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class GsonService {

    Gson gson = new Gson();

    private String json1 = "{ 'name' : 'yoojungil', 'corpName' : 'myalley' }";
    private String json2 = "{ 'data' : [{ 'name' : '유정일', 'classes' : '1학년2반', 'no' : '34' },{ 'name' : '유*서', 'classes' : '1학년3반', 'no' : '45' },{ 'name' : '유*아', 'classes' : '1학년9반', 'no' : '99' }] }";
    private String jsonData1 = null;
    private String jsonData2 = null;
    private String jsonData3 = null;

    public String gson1() throws Exception {
        /**
         * json파싱을 통한 단계적 변환
         * JsonParse > JsonElement > JsonObject
         */
        JsonParser jsonParser = new JsonParser();
        JsonElement jsonElement = jsonParser.parse(json1);
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        if (log.isDebugEnabled()) {
            log.debug("name : " + jsonObject.get("name").getAsString());
            log.debug("corpName : " + jsonObject.get("corpName").getAsString());
        }

        /**
         * gson.fromJson이용해 JsonObject
         */
        JsonObject jsonObject1 = gson.fromJson(json1, JsonObject.class);
        if (log.isDebugEnabled()) {
            log.debug("1name : " + jsonObject1.get("name").getAsString());
            log.debug("1corpName : " + jsonObject1.get("corpName").getAsString());
        }

        /**
         * gson.fromJson이용해 JsonObject > JsonArray
         */
        JsonObject jsonObject2 = gson.fromJson(json2, JsonObject.class);
        JsonArray jsonArray2 = jsonObject2.getAsJsonArray("data");
        for (Object each : jsonArray2) {
            JsonObject tempJsonObject = (JsonObject) each;
            if (log.isDebugEnabled()) {
                log.debug("2name : " + tempJsonObject.get("name").getAsString());
            }
        }

        /**
         * gson.fromJson이용해 JsonObject > JsonElement > JsonArray
         */
        JsonObject jsonObject3 = gson.fromJson(json2, JsonObject.class);
        JsonElement jsonElement3 = jsonObject3.get("data");
        if (jsonElement.isJsonArray()) {
            JsonArray jsonArray3 = jsonElement.getAsJsonArray();
            for (Object each : jsonArray3) {
                JsonObject tempJsonObject = (JsonObject) each;
                if (log.isDebugEnabled()) {
                    log.debug("3name : " + tempJsonObject.get("name").getAsString());
                }
            }
        }

        /**
         * Key없음
         * 단순 배열 JsonArray
         */
        createJsonArray();
        JsonArray jsonArray4 = gson.fromJson(jsonData1, JsonArray.class);
        for (Object each : jsonArray4) {
            JsonObject tempJsonObject = (JsonObject) each;
            if (log.isDebugEnabled()) {
                log.debug("4no : " + tempJsonObject.get("no").getAsString());
                log.debug("4name : " + tempJsonObject.get("name").getAsString());
            }
        }

        /**
         * key있음 + List형태의 반복부
         * gson.fromJson이용해 JsonObject
         * gson.fromJson이용해 JsonObject > JsonArray
         */
        createJsonSumArray();
        JsonObject jsonObject5 = gson.fromJson(jsonData2, JsonObject.class);
        String father = jsonObject5.get("father").getAsString();
        String mother = jsonObject5.get("mother").getAsString();
        String familyCnt = jsonObject5.get("familyCnt").getAsString();
        if (log.isDebugEnabled()) {
            log.debug("5father : " + father);
            log.debug("5mother : " + mother);
            log.debug("5familyCnt : " + familyCnt);
        }
        JsonArray jsonArray5 = jsonObject5.getAsJsonArray("familyList");
        if (jsonArray5.isJsonArray()) {
            for (Object each : jsonArray5) {
                JsonObject tempJsonObject = (JsonObject) each;
                String no = tempJsonObject.get("no").getAsString();
                String name = tempJsonObject.get("name").getAsString();
                String classes = tempJsonObject.get("classes").getAsString();
                String hobby = tempJsonObject.get("hobby").getAsString();
                if (log.isDebugEnabled()) {
                    log.debug("5no : " + no);
                    log.debug("5name : " + name);
                    log.debug("5classes : " + classes);
                    log.debug("5hobby : " + hobby);
                }
            }
        }

        /**
         * Key있음 + List형태의 반봅구
         * gson.fromJson이용해 FamilyMst
         */
        FamilyMst familyMst = gson.fromJson(jsonData2, FamilyMst.class);
        if(log.isDebugEnabled()) {
            log.debug("familyMst : " + familyMst);
            log.debug("fmailyMst.father : " + familyMst.getFather());
            log.debug("fmailyMst.moenther : " + familyMst.getMother());
            log.debug("fmailyMst.familyCnt : " + familyMst.getFamilyCnt());
        }
        List<FamilyDetail> familyDetailList = familyMst.getFamilyList();
        for(FamilyDetail each : familyDetailList) {
            if(log.isDebugEnabled()) {
                log.debug("familyDetail.no : " + each.getNo());
                log.debug("familyDetail.name : " + each.getName());
                log.debug("familyDetail.classes : " + each.getClasses());
                log.debug("familyDetail.hobby : " + each.getHobby());
            }
        }

        /**
         * Map을 json변환 후 JsonArray
         */
        createMap2JsonArray();
        JsonArray jsonArray6 = gson.fromJson(jsonData3, JsonArray.class);
        if(jsonArray6.isJsonArray()) {
            for(Object each : jsonArray6) {
                JsonObject tempJsonObject = (JsonObject) each;
                if(log.isDebugEnabled()) {
                    log.debug("6no : " + tempJsonObject.get("no").getAsString());
                    log.debug("6name : " + tempJsonObject.get("name").getAsString());
                }
            }
        }

        return null;
    }

    public void createJsonArray() {
        JsonArray jsonArray = new JsonArray();
        JsonObject jsonObject = null;

        jsonObject = new JsonObject();
        jsonObject.addProperty("no", "1");
        jsonObject.addProperty("name", "유정일");
        jsonObject.addProperty("classes", "2학년");
        jsonObject.addProperty("hobby", "낚시");
        jsonArray.add(jsonObject);

        jsonObject = new JsonObject();
        jsonObject.addProperty("no", "2");
        jsonObject.addProperty("name", "유*우");
        jsonObject.addProperty("classes", "1학년");
        jsonObject.addProperty("hobby", "그림");
        jsonArray.add(jsonObject);

        jsonData1 = gson.toJson(jsonArray);
    }

    public void createJsonSumArray() {
        JsonObject jsonObject = null;
        JsonArray jsonArray = new JsonArray();
        JsonObject jsonEach = null;

        jsonEach = new JsonObject();
        jsonEach.addProperty("no", "1");
        jsonEach.addProperty("name", "유*서");
        jsonEach.addProperty("classes", "2학년");
        jsonEach.addProperty("hobby", "낚시");
        jsonArray.add(jsonEach);

        jsonEach = new JsonObject();
        jsonEach.addProperty("no", "2");
        jsonEach.addProperty("name", "유*우");
        jsonEach.addProperty("classes", "1학년");
        jsonEach.addProperty("hobby", "태권도");
        jsonArray.add(jsonEach);

        jsonEach = new JsonObject();
        jsonEach.addProperty("no", "3");
        jsonEach.addProperty("name", "유*아");
        jsonEach.addProperty("classes", "1학년");
        jsonEach.addProperty("hobby", "발레");
        jsonArray.add(jsonEach);

        jsonObject = new JsonObject();
        jsonObject.addProperty("father", "유정일");
        jsonObject.addProperty("mother", "최*영");
        jsonObject.addProperty("familyCnt", "5");
        jsonObject.add("familyList", jsonArray);//반복부는 JsonArray Object 형태로 Setting.

        jsonData2 = gson.toJson(jsonObject);
    }

    public void createMap2JsonArray() {
        JsonArray jsonArray = new JsonArray();
        Map<String, Object> map = null;

        map = new HashMap<String, Object>();
        map.put("no", "9");
        map.put("name", "유정일");
        jsonArray.add(gson.fromJson(gson.toJson(map), JsonObject.class));

        map = new HashMap<String, Object>();
        map.put("no", "10");
        map.put("name", "유*서");
        jsonArray.add(gson.fromJson(gson.toJson(map), JsonObject.class));

        jsonData3 = gson.toJson(jsonArray);
    }
}
