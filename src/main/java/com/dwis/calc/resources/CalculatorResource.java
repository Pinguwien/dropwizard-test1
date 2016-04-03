package com.dwis.calc.resources;

import com.dwis.calc.service.CalculatorService;
import com.dwis.calc.service.CalculatorServiceImpl;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by Dominik
 * on 03.04.16.
 */
@Path("/RomanCalc/")
@Produces(MediaType.APPLICATION_JSON)
public class CalculatorResource {

    //TODO: DI
    private CalculatorService romanCalculator = new CalculatorServiceImpl();

    public CalculatorResource(){}

    @GET
    @Path("{leftNum}/{operation}/{rightNum}")
    public String calculate(@PathParam("leftNum")String leftNumber,
                            @PathParam("operation")String operation,
                            @PathParam("rightNum")String rightNumber){

        String result = romanCalculator.calculate(leftNumber,operation,rightNumber);
        //TODO: Add DB Entry containing user, calculation, calcResult. may be easy, though.
        return result;
    }
}
