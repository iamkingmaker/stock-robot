/** 
 *  Generated by OpenJPA MetaModel Generator Tool.
**/

package model.database.jpa.tables;

import java.util.Date;
import javax.persistence.metamodel.SingularAttribute;

@javax.persistence.metamodel.StaticMetamodel
(value=model.database.jpa.tables.PortfolioHistory.class)
@javax.annotation.Generated
(value="org.apache.openjpa.persistence.meta.AnnotationProcessor6",date="Wed May 16 20:21:34 CEST 2012")
public class PortfolioHistory_ {
    public static volatile SingularAttribute<PortfolioHistory,Long> amount;
    public static volatile SingularAttribute<PortfolioHistory,Date> buyDate;
    public static volatile SingularAttribute<PortfolioHistory,PortfolioEntity> portfolio;
    public static volatile SingularAttribute<PortfolioHistory,Date> soldDate;
    public static volatile SingularAttribute<PortfolioHistory,StockPrices> stockPrice;
    public static volatile SingularAttribute<PortfolioHistory,StockPrices> stockSoldPrice;
}
