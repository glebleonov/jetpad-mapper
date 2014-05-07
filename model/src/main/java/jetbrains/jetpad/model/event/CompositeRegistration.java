/*
 * Copyright 2012-2014 JetBrains s.r.o
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
package jetbrains.jetpad.model.event;

import jetbrains.jetpad.base.Registration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CompositeRegistration implements Registration {
  private List<Registration> myRegistrations;
  private boolean myRemoved;
  private boolean myRemoving;

  public CompositeRegistration(Registration... regs) {
    myRegistrations = new ArrayList<>(regs.length);
    myRegistrations.addAll(Arrays.asList(regs));
  }

  public CompositeRegistration add(Registration r) {
    myRegistrations.add(r);
    return this;
  }

  public CompositeRegistration add(Registration... rs) {
    for (Registration r : rs) {
      add(r);
    }
    return this;
  }

  public boolean isEmpty() {
    return myRegistrations.isEmpty();
  }

  @Override
  public void remove() {
    if (myRemoved || myRemoving) {
      throw new IllegalStateException();
    }

    myRemoving = true;
    try {
      for (Registration r : myRegistrations) {
        try {
          r.remove();
        } catch (Throwable t) {
          Callbacks.handleException(t);
        }
      }
    } finally {
      myRemoving = false;
    }
    myRemoved = true;
  }
}