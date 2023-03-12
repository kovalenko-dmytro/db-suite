package com.gmail.apachdima.dbsuite.compareservice.service.matcher;

import com.gmail.apachdima.dbsuite.compareservice.dto.matcher.MatchContext;
import com.gmail.apachdima.dbsuite.compareservice.dto.matcher.MatchNode;

public interface IMatcher {

    MatchNode match(MatchContext context);
}
