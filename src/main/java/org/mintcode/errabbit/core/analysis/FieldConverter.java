package org.mintcode.errabbit.core.analysis;

import java.util.HashMap;
import java.util.Map;

/**
 * Convert Data scheme to UI field name
 * Created by soleaf on 15. 8. 2..
 */
public class FieldConverter {

    private static final Map<String,String> dict;
    static
    {
        dict = new HashMap<>();
        dict.put("rabbitId", "rabbitId");
        dict.put("loggingEventDateInt", "loggingEventDateInt");
        dict.put("loggingEvent.level", "level");
        dict.put("loggingEvent.categoryName", "categoryName");
    }

    /**
     * collectionField(dataScheme) to UI field Name
     * @param collectionField
     * @return
     */
    public static String toFieldName(String collectionField){
        return dict.get(collectionField);
    }
}
