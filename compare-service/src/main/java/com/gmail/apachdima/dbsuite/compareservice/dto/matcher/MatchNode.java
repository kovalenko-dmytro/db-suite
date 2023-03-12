package com.gmail.apachdima.dbsuite.compareservice.dto.matcher;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MatchNode {

    MatchNode parent;
    List<MatchNode> children;

    @Override
    public String toString() {
        return super.toString();
    }
}
