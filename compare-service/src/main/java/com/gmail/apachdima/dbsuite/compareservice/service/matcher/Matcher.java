package com.gmail.apachdima.dbsuite.compareservice.service.matcher;

import com.gmail.apachdima.dbsuite.compareservice.dto.matcher.MatchContext;
import com.gmail.apachdima.dbsuite.compareservice.dto.matcher.MatchNode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Matcher implements IMatcher {

    @Override
    public MatchNode match(MatchContext context) {
        return null;
    }
}
