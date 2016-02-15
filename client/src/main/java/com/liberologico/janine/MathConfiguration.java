package com.liberologico.janine;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MathConfiguration
{
    /**
     * BigDecimal output precision
     */
    public final static int defaultPrecision = 2;

    /**
     * BigDecimal calculation precision
     */
    public final static int computationPrecision = 4;

    /**
     * BigDecimal roundingMode
     */
    public final static RoundingMode roundingMode = RoundingMode.HALF_EVEN;

    public final static BigDecimal ONE_HUNDRED = new BigDecimal( 100 );

    /**
     * Return value + percentage%
     */
    public static BigDecimal calculatePercentage( BigDecimal value, BigDecimal percentage )
    {
        if ( value == null || percentage == null ) throw new IllegalArgumentException( "arguments can't be null" );
        return value.divide( ONE_HUNDRED, computationPrecision, roundingMode ).multiply( percentage );
    }
}
