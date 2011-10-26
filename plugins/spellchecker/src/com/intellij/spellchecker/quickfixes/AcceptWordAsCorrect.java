/*
 * Copyright 2000-2009 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.intellij.spellchecker.quickfixes;

import com.intellij.codeInspection.ProblemDescriptor;
import com.intellij.openapi.actionSystem.Anchor;
import com.intellij.openapi.project.Project;
import com.intellij.spellchecker.SpellCheckerManager;
import com.intellij.spellchecker.util.SpellCheckerBundle;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;


public class AcceptWordAsCorrect implements SpellCheckerQuickFix {
  private String myWord;

  public AcceptWordAsCorrect(String word) {
    myWord = word;
  }

  public AcceptWordAsCorrect() {
  }

  @NotNull
  public String getName() {
    return myWord != null ? SpellCheckerBundle.message("add.0.to.dictionary", myWord) : SpellCheckerBundle.message("add.to.dictionary");
  }

  @NotNull
  public String getFamilyName() {
    return SpellCheckerBundle.message("spelling");
  }

  @NotNull
  public Anchor getPopupActionAnchor() {
    return Anchor.LAST;
  }

  public void applyFix(@NotNull Project project, @NotNull ProblemDescriptor descriptor) {
    SpellCheckerManager spellCheckerManager = SpellCheckerManager.getInstance(project);
    spellCheckerManager.acceptWordAsCorrect(myWord, project);
  }

  public Icon getIcon(int flags) {
    return new ImageIcon(ShowSuggestions.class.getResource("spellcheck.png"));
  }
}
