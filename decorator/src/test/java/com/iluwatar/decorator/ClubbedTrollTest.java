/**
 * The MIT License
 * Copyright (c) 2014-2016 Ilkka Seppälä
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.iluwatar.decorator;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

/**
 * Tests for {@link ClubbedTroll}
 */
public class ClubbedTrollTest {
  
  @Test
  public void testSmartHostile() throws Exception {
    // Create a normal troll first, but make sure we can spy on it later on.
    final Troll simpleTroll = spy(new SimpleTroll());
    
    // Now we want to decorate the troll to make it stronger ...
    final Troll clubbed = new ClubbedTroll(simpleTroll);
    assertEquals(20, clubbed.getAttackPower());
    verify(simpleTroll, times(1)).getAttackPower();
    
    // Check if the clubbed troll actions are delegated to the decorated troll
    clubbed.attack();
    verify(simpleTroll, times(1)).attack();
    
    clubbed.fleeBattle();
    verify(simpleTroll, times(1)).fleeBattle();
    verifyNoMoreInteractions(simpleTroll);
  }
}
