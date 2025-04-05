/*
 * The MIT License
 *
 * Copyright 2016 Pawel Marynowski.
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
package pattypan.elements;

import javafx.geometry.HPos;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import pattypan.Util;

public class WikiProgressBar extends GridPane {

  String[] labels;
  double progress;

  public WikiProgressBar(double progress, String... labels) {
    super();
    this.labels = labels;
    if (labels.length == 3) {
      createContent(progress);
    } else if (labels.length == 4) {
      createContent4(progress);
    }
  }

  private Pane createDot(double number, int translateX) {
    Circle c = new Circle(5);
    c.getStyleClass().add("mw-ui-progressbar-dot" + (isActive(number) ? "-active" : ""));

    Pane p = new Pane(c);
    p.setTranslateX(translateX);
    p.setTranslateY(-2);
    return p;
  }

  private WikiLabel createLabel(double number, String label) {
    return new WikiLabel(label).setClass("mw-ui-progressbar-text" + (isActive(number) ? "-active" : ""));
  }

  private boolean isActive(double number) {
    return progress >= (number / 2.0);
  }

  private GridPane createContent(double progress) {
    this.progress = progress;
    this.setMinWidth(420);
    this.setMaxWidth(420);
    this.getStyleClass().add("mw-ui-progressbar-container");

    this.getColumnConstraints().addAll(
            Util.newColumn(33, "%", HPos.LEFT),
            Util.newColumn(33, "%", HPos.CENTER),
            Util.newColumn(33, "%", HPos.RIGHT));

    ProgressBar pb = new ProgressBar(progress);
    pb.getStyleClass().addAll("mw-ui-progressbar");
    pb.setMinWidth(420);
    pb.setMaxWidth(420);
    pb.setMaxHeight(5);
    this.add(pb, 0, 0, 3, 1);

    this.addRow(1,
            createDot(0.0, 1),
            createDot(1.0, 70),
            createDot(2.0, 140));

    this.addRow(2,
            createLabel(0.0, labels[0]).setTranslateByHalf(false),
            createLabel(1.0, labels[1]),
            createLabel(2.0, labels[2]).setTranslateByHalf(true)
    );
    return this;
  }

  private GridPane createContent4(double progress) {
    this.progress = progress;
    this.setMinWidth(420);
    this.setMaxWidth(420);
    this.getStyleClass().add("mw-ui-progressbar-container");

    this.getColumnConstraints().addAll(
            Util.newColumn(25, "%", HPos.LEFT),
            Util.newColumn(25, "%", HPos.CENTER),
            Util.newColumn(25, "%", HPos.CENTER),
            Util.newColumn(25, "%", HPos.RIGHT));

    ProgressBar pb = new ProgressBar(progress);
    pb.getStyleClass().addAll("mw-ui-progressbar");
    pb.setMinWidth(420);
    pb.setMaxWidth(420);
    pb.setMaxHeight(5);
    this.add(pb, 0, 0, 3, 1);

    this.addRow(1,
            createDot(0.0, 1),
            createDot(0.66, 33),
            createDot(1.32, 66),
            createDot(2.0, 100));

    this.addRow(2,
            createLabel(0.0, labels[0]).setTranslateByHalf(false),
            createLabel(0.66, labels[1]).setTranslateByHalf(false),
            createLabel(1.32, labels[2]).setTranslateByHalf(true),
            createLabel(2.0, labels[3]).setTranslateByHalf(true)
    );
    return this;
  }
}
