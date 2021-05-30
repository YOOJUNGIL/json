package com.myalley.jason.dao.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FamilyMst {
    private String father;
    private String mother;
    private String familyCnt;
    private List<FamilyDetail> familyList;
}
