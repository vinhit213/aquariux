package com.txvinh.aquariux.mapper;

import com.txvinh.aquariux.domain.BinancePriceData;
import com.txvinh.aquariux.domain.HuobiPriceData;
import com.txvinh.aquariux.domain.PriceData;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;

@Mapper
public interface PriceDataMapper {
    PriceDataMapper INSTANCE = Mappers.getMapper(PriceDataMapper.class);

    @BeanMapping(qualifiedByName = "binancePriceToPrice")
    @Mapping(target = "bidPrice", qualifiedByName = "stringToBigDecimal")
    @Mapping(target = "bidQty", qualifiedByName = "stringToBigDecimal")
    @Mapping(target = "askPrice", qualifiedByName = "stringToBigDecimal")
    @Mapping(target = "askQty", qualifiedByName = "stringToBigDecimal")
    PriceData binancePriceToPrice(BinancePriceData binancePriceData);

    @BeanMapping(qualifiedByName = "huobiPriceToPrice")
    @Mapping(target = "symbol", source = "symbol", qualifiedByName = "upperCase")
    @Mapping(target = "bidPrice", source = "bid")
    @Mapping(target = "bidQty", source = "bidSize")
    @Mapping(target = "askPrice", source = "ask")
    @Mapping(target = "askQty", source = "askSize")
    PriceData huobiPriceToPrice(HuobiPriceData huobiPriceData);

    @Named("stringToBigDecimal")
    default BigDecimal stringToBigDecimal(String val) {
        if (!StringUtils.hasLength(val)) {
            return null;
        }
        BigDecimal result = BigDecimal.ZERO;
        try{
            result = BigDecimal.valueOf(Double.parseDouble(val));
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Named("upperCase")
    default String upperCase(String val) {
        return val.toUpperCase();
    }
}
