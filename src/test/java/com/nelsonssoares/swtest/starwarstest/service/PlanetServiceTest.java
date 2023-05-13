package com.nelsonssoares.swtest.starwarstest.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.nelsonssoares.swtest.starwarstest.domain.Planet;
import com.nelsonssoares.swtest.starwarstest.repository.PlanetRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Optional;

import static com.nelsonssoares.swtest.starwarstest.common.PlanetConstants.PLANET;
import static com.nelsonssoares.swtest.starwarstest.common.PlanetConstants.INVALID_PLANET;
//Usar quando é sómente teste de unidade
@ExtendWith(MockitoExtension.class)
//@SpringBootTest(classes = PlanetService.class) --> injeta muitas coisas sem nescessidade para este tipo de teste
public class PlanetServiceTest {
    
   // @Autowired --> somente quando o @SpringBootTest estiver ativo
    @InjectMocks //--> já injeta as depedencias de planetservice
    private PlanetService planetService;

    //@MockBean--> somente quando o @SpringBootTest estiver ativo
    @Mock //-> dubla/mocka a dependencia de planetService
    private PlanetRepository planetRepository;

    @Test
    public void createPlanet_WithValidData_ReturnsPlanet(){
        //AAA
        //Arrange
        when(planetRepository.save(PLANET)).thenReturn(PLANET);
        //Act
        Planet sut = planetService.create(PLANET);
        //Assert
        assertThat(sut).isEqualTo(PLANET);
    }

    @Test
    public void createPlanet_WithInValidData_ThrowsException(){
        when(planetRepository.save(INVALID_PLANET)).thenThrow(RuntimeException.class);
        
        assertThatThrownBy(()-> planetService.create(INVALID_PLANET)).isInstanceOf(RuntimeException.class);   
    }

    @Test
    public void getPlanet_ByExistingId_ReturnsPlanet(){
        when(planetRepository.findById(anyLong())).thenReturn(Optional.of(PLANET));
        
        Optional<Planet> sut = planetService.get(1L);
        
        assertThat(sut).isNotEmpty();
        assertThat(sut.get()).isEqualTo(PLANET);
    }   

    @Test
    public void getPlanet_ByUnexistingId_ReturnsEmpty(){
        when(planetRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Planet> sut = planetService.get(1L);

        assertThat(sut).isEmpty();
    }

    @Test
    public void getPlanet_ByExistingName_ReturnsPlanet(){
        when(planetRepository.findByName(PLANET.getName())).thenReturn(Optional.of(PLANET));
        
        Optional<Planet> sut = planetService.getByName(PLANET.getName());
        
        assertThat(sut).isNotEmpty();
        assertThat(sut.get()).isEqualTo(PLANET);
    }

    @Test
    public void getPlanet_ByUnexistingName_ReturnsEmpty(){
        String name = "Unexisting name";
        when(planetRepository.findByName(name)).thenReturn(Optional.empty());

        Optional<Planet> sut = planetService.getByName(name);

        assertThat(sut).isEmpty();
    }
}
