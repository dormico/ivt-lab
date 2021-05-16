package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class GT4500Test {

  private TorpedoStore mockTS1;
  private TorpedoStore mockTS2;
  private GT4500 ship;

  @BeforeEach
  public void init(){
    mockTS1 = mock(TorpedoStore.class);
    mockTS2 = mock(TorpedoStore.class);    
    
    this.ship = new GT4500(mockTS1, mockTS2);
  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange
    when(mockTS1.isEmpty()).thenReturn(false);
    when(mockTS2.isEmpty()).thenReturn(false);
    when(mockTS1.fire(1)).thenReturn(true);
    when(mockTS2.fire(1)).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);
    verify(mockTS1, times(1)).isEmpty();
    verify(mockTS1, times(1)).fire(1);
    // Assert
    assertEquals(true, result);
  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange
    when(mockTS1.isEmpty()).thenReturn(false);
    when(mockTS2.isEmpty()).thenReturn(false);
    when(mockTS1.fire(1)).thenReturn(true);
    when(mockTS2.fire(1)).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);
    verify(mockTS1, times(1)).isEmpty();
    verify(mockTS2, times(1)).isEmpty();
    verify(mockTS1, times(1)).fire(1);
    verify(mockTS2, times(1)).fire(1);
    // Assert
    assertEquals(true, result);
  }

  @Test
  public void fireTorpedo_Single_Only_One(){
    when(mockTS1.isEmpty()).thenReturn(false);
    when(mockTS2.isEmpty()).thenReturn(false);
    when(mockTS1.fire(1)).thenReturn(true);
    when(mockTS2.fire(1)).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    verify(mockTS1, times(1)).isEmpty();
    verify(mockTS1, times(1)).fire(1);
    verify(mockTS2, times(0)).isEmpty();    
    verify(mockTS2, times(0)).fire(1);
    // Assert
    assertEquals(true, result);
  }

  @Test
  public void fireTorpedo_Single_Primary_First(){
    when(mockTS1.isEmpty()).thenReturn(false);
    when(mockTS2.isEmpty()).thenReturn(false);
    when(mockTS1.fire(1)).thenReturn(true);
    when(mockTS2.fire(1)).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);
    
    verify(mockTS1, times(1)).isEmpty();
    verify(mockTS1, times(1)).fire(1);
    verify(mockTS2, times(0)).isEmpty();    
    verify(mockTS2, times(0)).fire(1);
    // Assert
    assertEquals(true, result);
  }

  @Test
  public void fireTorpedo_Single_Empty_Store(){
    when(mockTS1.isEmpty()).thenReturn(true);
    when(mockTS2.isEmpty()).thenReturn(false);
    when(mockTS1.fire(1)).thenReturn(false);
    when(mockTS2.fire(1)).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);
    
    verify(mockTS1, times(1)).isEmpty();
    verify(mockTS1, times(0)).fire(1);
    verify(mockTS2, times(1)).isEmpty();    
    verify(mockTS2, times(1)).fire(1);
    // Assert
    assertEquals(true, result);
  }

  @Test
  public void fireTorpedo_Single_Fired_Last(){
    when(mockTS1.isEmpty()).thenReturn(false);
    when(mockTS2.isEmpty()).thenReturn(false);
    when(mockTS1.fire(1)).thenReturn(true);
    when(mockTS2.fire(1)).thenReturn(true);
    // Act
    ship.fireTorpedo(FiringMode.SINGLE);
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);
    
    verify(mockTS1, times(1)).isEmpty();
    verify(mockTS1, times(1)).fire(1);
    verify(mockTS2, times(1)).isEmpty();    
    verify(mockTS2, times(1)).fire(1);
    // Assert
    assertEquals(true, result);
  }

  @Test
  public void fireTorpedo_All_Fire_Both(){
    when(mockTS1.isEmpty()).thenReturn(false);
    when(mockTS2.isEmpty()).thenReturn(false);
    when(mockTS1.fire(1)).thenReturn(true);
    when(mockTS2.fire(1)).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);
    
    verify(mockTS1, times(1)).isEmpty();
    verify(mockTS1, times(1)).fire(1);
    verify(mockTS2, times(1)).isEmpty();    
    verify(mockTS2, times(1)).fire(1);
    // Assert
    assertEquals(true, result);
  }

  @Test
  public void fireTorpedo_Single_First_After_Empty(){
    when(mockTS1.isEmpty()).thenReturn(false);
    when(mockTS2.isEmpty()).thenReturn(true);
    when(mockTS1.fire(1)).thenReturn(true);
    when(mockTS2.fire(1)).thenReturn(false);
    // Act
    ship.fireTorpedo(FiringMode.SINGLE);
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);
    
    verify(mockTS1, times(2)).isEmpty();
    verify(mockTS1, times(2)).fire(1);
    verify(mockTS2, times(1)).isEmpty();    
    verify(mockTS2, times(0)).fire(1);
    // Assert
    assertEquals(true, result);
  }
}
