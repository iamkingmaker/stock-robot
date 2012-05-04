
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import portfolio.PortfolioHandlerTest;

import robot.AstroRecieverTest;
import robot.RobotSchedulerTest;
import robot.TraderTest;
import scraping.ParserStockTest;
import scraping.parser.SimulationRunnerTest;
import simulation.SimulationHandlerTest;
import utils.FinancialLongConverterTest;
import utils.PairTest;
import view.components.ItemCmbPortfolioTest;

import database.jpa.JPATest;
import database.jpa.MainBasicJPATest;

/**
 * Class to wrap other unit test classes, so we can test everything we want to in one single run
 * 
 * <p>
 * 
 * TODO: Right now JPA needs to have the -javaagent 
 * -javaagent:/PATH-TO-YOUR-OPENJPA2/stock-robot/stockrobot/jar/openjpa-all-2.2.0.jar
 * 
 * </p>
 * 
 * @author kristian
 *
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
	FinancialLongConverterTest.class,
	PairTest.class,
	JPATest.class,
	MainBasicJPATest.class,
	SimulationHandlerTest.class,
	SimulationRunnerTest.class,
	ItemCmbPortfolioTest.class,
	AstroRecieverTest.class,
	ParserStockTest.class,
	PortfolioHandlerTest.class,
	TraderTest.class,
	RobotSchedulerTest.class
})

public class TestSuiteForEverything {
// nothing goes here	 
}