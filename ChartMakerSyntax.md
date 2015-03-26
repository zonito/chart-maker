#Chart Maker is a Google wave application as a Robot.

## Introduction ##
You can add charts in wave are follows:

  * Line Charts
  * Bar Charts
  * Pie Charts
  * Venn Diagram
  * Radar Chart

Used [Google Chart APIs](http://code.google.com/apis/chart/)

### Details ###

To use these charts in wave, please follow below syntax:

```
  Values = 10,20,30 (Numbers separated by Comma). 
    {lc Values} (Line With Axis)
    {ls Values} (Line Without Axis)
    {bhs Values} (Horizontal Bars)
    {bvs Values} (Vertical Bars)
    {p Values} (Simple Pie chart)
    {p3 Values} (3D Pie chart)
    {v Values} (Venn diagram)
    {r Values} (Radar Chart)
```

### Example ###

```
  {p3 10,30,60,43}
```
> http://chart.apis.google.com/chart?chs=250x100&chl=10|30|60|43&chdl=10|30|60|43&cht=p3&chd=t:10,30,60,43