
package com.nelsonssoares.swtest.starwarstest.common;

import com.nelsonssoares.swtest.starwarstest.domain.Planet;

public class PlanetConstants {
    public static final Planet PLANET = new Planet("name","climate","terrain");
    public static final Planet INVALID_PLANET = new Planet("", "climate", "terrain");
}   
