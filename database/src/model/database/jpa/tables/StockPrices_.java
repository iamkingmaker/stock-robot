/** 
 *  Generated by OpenJPA MetaModel Generator Tool.
**/

package model.database.jpa.tables;

import java.util.Date;
import javax.persistence.metamodel.SingularAttribute;

@javax.persistence.metamodel.StaticMetamodel
(value=model.database.jpa.tables.StockPrices.class)
@javax.annotation.Generated
(value="org.apache.openjpa.persistence.meta.AnnotationProcessor6",date="Wed May 16 20:21:34 CEST 2012")
public class StockPrices_ {
    public static volatile SingularAttribute<StockPrices,Long> buy;
    public static volatile SingularAttribute<StockPrices,Long> latest;
    public static volatile SingularAttribute<StockPrices,Long> sell;
    public static volatile SingularAttribute<StockPrices,StockNames> stockName;
    public static volatile SingularAttribute<StockPrices,Long> stockid;
    public static volatile SingularAttribute<StockPrices,Date> time;
    public static volatile SingularAttribute<StockPrices,Integer> volume;
}
