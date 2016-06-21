/*
 * SuperSimpleStocks.java, project: SuperSimpleStocks. Created on Jun 16, 2016 by Calin BOCSITAN
 * 
 * Requirements
 * 1. Provide working source code that will :-
 *  a. For a given stock, 
 *      i. calculate the dividend yield
 *      ii. calculate the P/E Ratio
 *      iii. record a trade, with timestamp, quantity of shares, buy or sell indicator and price
 *      iv. Calculate Stock Price based on trades recorded in past 15 minutes
 *  b. Calculate the GBCE All Share Index using the geometric mean of prices for all stocks
 *  
 *  Constraints & Notes
 *  1.  Written in one of these languages:
 *      Java, C#, C++, Python
 *  2. No database or GUI is required, all data need only be held in memory
 *  3. Formulas and data provided are simplified representations for the purpose of this exercise
 */
package ro.room529.supersimplestocks;

import java.util.Vector;

import ro.room529.supersimplestocks.entities.Trade;

/**
 * 
 * @author Calin BOCSITAN
 * @version
 * 	
 */
public class SuperSimpleStocks {
    
    public static final String STOCK_TYPE_PREFERRED = "Preferred";
    public static final String STOCK_TYPE_COMMON = "Common";
    
    private float tickerPrice = 124f;
    
    private Vector<Trade> trades = null;

    enum GBCEStock {
        
        TEA ("TEA", "Common", 0, 0, 100),
        POP ("POP", "Common", 8, 0, 100),
        ALE ("ALE", "Common", 23, 0, 60),
        GIN ("GIN", "Preferred", 8, 2, 100),
        JOE ("JOE", "Common", 13, 0, 250);
        
        private final String stockSymbol;
        private final String type;
        private final int lastDividend;
        private final int fixedDividend; // percentage
        private final int parValue;
        
        
        /**
         * constructor 
         * 
         * @author Calin BOCSITAN
         */
        private GBCEStock(String stockSymbol, String type, int lastDividend, int fixedDividend, int parValue) {
            
            this.stockSymbol = stockSymbol;
            this.type = type;
            this.lastDividend = lastDividend;
            this.fixedDividend = fixedDividend;
            this.parValue = parValue;
        }


        /**
         * 
         * @return Returns the stockSymbol.
         * @author Calin BOCSITAN
         */
        public String stockSymbol() {

            return stockSymbol;
        }


        /**
         * 
         * @return Returns the type.
         * @author Calin BOCSITAN
         */
        public String type() {

            return type;
        }


        /**
         * 
         * @return Returns the lastDividend.
         * @author Calin BOCSITAN
         */
        public int lastDividend() {

            return lastDividend;
        }


        /**
         * 
         * @return Returns the fixedDividend.
         * @author Calin BOCSITAN
         */
        public int fixedDividend() {

            return fixedDividend;
        }


        /**
         * 
         * @return Returns the parValue.
         * @author Calin BOCSITAN
         */
        public int parValue() {

            return parValue;
        }
        
    }

    /**
     * the 
     * 
     * @param args
     * @author Calin BOCSITAN
     */
    public static void main(String[] args) {
        
        SuperSimpleStocks superSimpleStocks = new SuperSimpleStocks();
        
        float dividentYield = superSimpleStocks.calculateDividendYield(GBCEStock.POP);
        System.out.println(dividentYield);
        
        float peRatio = superSimpleStocks.calculatePERatio(dividentYield);
        System.out.println(peRatio);
        
        superSimpleStocks.recordTrade(GBCEStock.ALE, 123, true, 234.43);
        for (Trade trade : superSimpleStocks.getTrades()) {
            System.out.println(trade);
        }
        
        double stockPrice = superSimpleStocks.calculateStockPrice(GBCEStock.GIN);
        System.out.println(stockPrice);

    }
    
    
    /**
     * Calculate the dividend yield.<br>
     * Formula for Common:<br>
     *  Last Dividend   <br>
     * ---------------  <br>
     *  Ticker Price    <br>
     * <br>
     * Formula for Preferred:<br>
     *  Fixed Dividend.Par Value    <br>
     * -------------------------    <br>
     *  Ticker Price                <br>
     * 
     * @author Calin BOCSITAN
     * @return 
     */
    private float calculateDividendYield(GBCEStock gbceStock) {
        
        System.out.println("Calculate the dividend yield for " + gbceStock.stockSymbol() + " with Last Dividend " + gbceStock.lastDividend());
        
        if (gbceStock.type().equals(STOCK_TYPE_PREFERRED)) {
            return gbceStock.parValue() * gbceStock.fixedDividend() / 100;
        } else {
            return gbceStock.lastDividend() / tickerPrice;
        }
    }
    
    
    /**
     * Calculate the P/E Ratio 
     * 
     * @author Calin BOCSITAN
     */
    private float calculatePERatio(float divident) {
        
        System.out.println("Calculate the P/E ratio for divident " + divident + " and ticker price " + tickerPrice);
        
        return tickerPrice / divident;

    }
    
    
    /**
     * Record a trade, with timestamp, quantity of shares, buy or sell indicator and price 
     * 
     * @author Calin BOCSITAN
     */
    private void recordTrade(GBCEStock gbceStock, long quantity, boolean buy, double price) {
        
        Trade trade = new Trade(gbceStock.stockSymbol(), quantity, buy, price);
        getTrades().add(trade);

    }
    
    
    /**
     * Calculate Stock Price based on trades recorded in past 15 minutes 
     * 
     * @author Calin BOCSITAN
     */
    private double calculateStockPrice(GBCEStock gbceStock) {
        
        System.out.println("Will record a trade every 5 miliseconds for 4500 miliseconds");
        
        double stockPrice = 0;
        double stockPriceDivident = 0;
        double stockPriceDivisor = 0;
        
        for (int i = 0; i < 4500; i++) {
            recordTrade(gbceStock, 234 * (i > 0 ? i : 1), (i % 2 == 0), 822.23 * (i > 0 ? i : 1));
            
            try {
                Thread.sleep(5);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        
        for (Trade trade : getTrades()) {
            if (!trade.getStockSymbol().equals(gbceStock.stockSymbol())) {
                continue;
            }
            
            stockPriceDivident += trade.getPrice() * trade.getQuantity();
            stockPriceDivisor += trade.getQuantity();
        }
        
        if (stockPriceDivisor != 0) {
            stockPrice = stockPriceDivident / stockPriceDivisor;
        }
        
        return stockPrice;

    }


    
    /**
     * 
     * @return Returns the trades.
     * @author Calin BOCSITAN
     */
    public Vector<Trade> getTrades() {
        
        if (trades == null) {
            trades = new Vector<Trade>();
        }
    
        return trades;
    }


    
    /**
     * 
     * @param trades The trades to set.
     * @author Calin BOCSITAN
     */
    public void setTrades(Vector<Trade> trades) {
    
        this.trades = trades;
    }
}
