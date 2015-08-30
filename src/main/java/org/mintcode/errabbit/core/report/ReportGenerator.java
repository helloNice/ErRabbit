package org.mintcode.errabbit.core.report;

import org.apache.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mintcode.errabbit.core.analysis.LogAggregationAnalysis;
import org.mintcode.errabbit.core.analysis.request.LogAnalysisRequest;
import org.mintcode.errabbit.core.analysis.result.AnalysisResultSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by soleaf on 15. 8. 27..
 */
@Service
public class ReportGenerator {

    Logger logger = LogManager.getLogger(getClass());

    SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");

    @Autowired
    LogAggregationAnalysis aggregation;

    public boolean generate(ReportDescription description, Date date){
        try{

            Integer targetDateInt = Integer.parseInt(format.format(date));

            return true;
        }
        catch (Exception e){
            logger.error(e.getMessage(), e);
            return false;
        }
    }

    private void generateClassRelatedReport(ReportDescription description, Integer targetDateInt){
        // Make logLevel count table group by rabbit.
        Set<String> levelSet = new HashSet<>();
        levelSet.add(Level.ERROR.toString());
        levelSet.add(Level.WARN.toString());
        levelSet.add(Level.FATAL.toString());

        List<String> group = new ArrayList<>();
        group.add("rabbitId");
        group.add("loggingEvent.categoryName");
        group.add("loggingEvent.loggingEvent.categoryName");

        LogAnalysisRequest req = new LogAnalysisRequest();
        req.setFilterRabbits(description.getTargets());
        req.setFilterBeginDate(targetDateInt);
        req.setFilterEndDate(targetDateInt);
        req.setFilterLevels(levelSet);

        AnalysisResultSet results = aggregation.aggregation(req);
    }

}