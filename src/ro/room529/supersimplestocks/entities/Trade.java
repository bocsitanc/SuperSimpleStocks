/*
 * Trade.java, project: SuperSimpleStocks. Created on Jun 22, 2016 by Calin BOCSITAN
 */
package ro.room529.supersimplestocks.entities;

import java.time.Instant;

/**
 * 
 * @author Calin BOCSITAN
 * @version
 * 	
 */
public class Trade {
    
    private Instant timestamp = null;
    
    private String stockSymbol = null;
    
    private long quantity = 0;
    
    private boolean buy = true;
    
    private double price = 0;
    
    
    /**
     * constructor 
     * 
     * @author Calin BOCSITAN
     */
    public Trade(String stockSymbol, long quantity, boolean buy, double price) {
        
        this(Instant.now(), stockSymbol, quantity, buy, price);
    }
    
    
    /**
     * constructor 
     * 
     * @author Calin BOCSITAN
     */
    public Trade(Instant timestamp, String stockSymbol, long quantity, boolean buy, double price) {
        
        this.timestamp = timestamp;
        this.stockSymbol = stockSymbol;
        this.quantity = quantity;
        this.buy = buy;
        this.price = price;
    }

    
    /**
     * 
     * @return Returns the timestamp.
     * @author Calin BOCSITAN
     */
    public Instant getTimestamp() {
    
        return timestamp;
    }

    
    /**
     * 
     * @param timestamp The timestamp to set.
     * @author Calin BOCSITAN
     */
    public void setTimestamp(Instant timestamp) {
    
        this.timestamp = timestamp;
    }


    
    /**
     * 
     * @return Returns the stockSymbol.
     * @author Calin BOCSITAN
     */
    public String getStockSymbol() {
    
        return stockSymbol;
    }


    
    /**
     * 
     * @param stockSymbol The stockSymbol to set.
     * @author Calin BOCSITAN
     */
    public void setStockSymbol(String stockSymbol) {
    
        this.stockSymbol = stockSymbol;
    }

    
    /**
     * 
     * @return Returns the quantity.
     * @author Calin BOCSITAN
     */
    public long getQuantity() {
    
        return quantity;
    }

    
    /**
     * 
     * @param quantity The quantity to set.
     * @author Calin BOCSITAN
     */
    public void setQuantity(long quantity) {
    
        this.quantity = quantity;
    }

    
    /**
     * 
     * @return Returns the buy.
     * @author Calin BOCSITAN
     */
    public boolean isBuy() {
    
        return buy;
    }

    
    /**
     * 
     * @param buy The buy to set.
     * @author Calin BOCSITAN
     */
    public void setBuy(boolean buy) {
    
        this.buy = buy;
    }

    
    /**
     * 
     * @return Returns the price.
     * @author Calin BOCSITAN
     */
    public double getPrice() {
    
        return price;
    }

    
    /**
     * 
     * @param price The price to set.
     * @author Calin BOCSITAN
     */
    public void setPrice(double price) {
    
        this.price = price;
    }


    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {

        return "Trade [timestamp = " + timestamp + ", stockSymbol = " + stockSymbol + ", quantity = " + quantity + ", buy = " + buy + ", price = " + price + "]";
    }

}
