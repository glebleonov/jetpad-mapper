/*
 * Copyright 2012-2015 JetBrains s.r.o
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jetbrains.jetpad.geometry;

import org.junit.Assert;
import org.junit.Test;

public class DoubleRectangleIntersectionTest {
  @Test
  public void simple() {
    DoubleRectangle r1 = new DoubleRectangle(new DoubleVector(0, 0), new DoubleVector(100, 100));
    DoubleRectangle r2 = new DoubleRectangle(new DoubleVector(50, 50), new DoubleVector(100, 100));

    Assert.assertEquals(new DoubleRectangle(new DoubleVector(50, 50), new DoubleVector(50, 50)), r1.intersect(r2));
  }

  @Test
  public void oneInsideOfAnother() {
    DoubleRectangle r1 = new DoubleRectangle(new DoubleVector(0, 0), new DoubleVector(100, 100));
    DoubleRectangle r2 = new DoubleRectangle(new DoubleVector(50, 50), new DoubleVector(10, 10));

    Assert.assertEquals(new DoubleRectangle(new DoubleVector(50, 50), new DoubleVector(10, 10)), r1.intersect(r2));
  }

  @Test
  public void noIntersection() {
    DoubleRectangle r1 = new DoubleRectangle(new DoubleVector(0, 0), new DoubleVector(50, 50));
    DoubleRectangle r2 = new DoubleRectangle(new DoubleVector(100, 100), new DoubleVector(50, 50));

    Assert.assertNull(r1.intersect(r2));
  }
}