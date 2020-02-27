package part

import (
  "time"
  "fmt"
)

type Visit struct {
  start, end time.Time
}

func (v Visit) Days() uint {
  var dif time.Duration = v.end.Sub(v.start)
  var days uint = uint(dif.Hours() / 24)
  return days + 1
}

func (v Visit)String() string {
  return fmt.Sprintf("(%v -> %v [days: %d])", v.start, v.end, v.Days())
}

func NewVisit(s, e time.Time) Visit {
  if e.Before(s) { // something is wrong
    fmt.Println("Visit: start is after end")
  }
  return Visit{s, e}
}
