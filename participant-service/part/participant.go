package part

import (
  "fmt"
)

type Participant struct {
  ID        uint      // ?
  age       uint
  firstName string
  lastName  string
  email     string
  phone     string
  extras    Extras
  visits    []Visit
  toPay     int       // ?
  paid      int       // ?
}


func NewParticipant(ID uint, firstName, lastName string, age uint) Participant {
  var p Participant
  p.ID = ID
  p.firstName = firstName
  p.lastName = lastName
  p.age = age

  // default extras
  p.extras = Extras{true, false, false, Tshirt{"M", "czarny", "meska"}}
  return p
}


func (p Participant)String() string {
  return fmt.Sprintf("#%v %s %s \n\tage %v \n\temail: %s \n\tphone: %s\n" + 
                     "\textras: %v \n\tvisits: %v, \n\tpayments %v/%v",
                      p.ID, p.firstName, p.lastName, p.age, p.email, p.phone,
                      p.extras, p.visits, p.paid, p.toPay)
}

func (p *Participant)AddVisit(v Visit) {
  p.visits = append(p.visits, v)
}

// Setters

func (p* Participant)SetContact(phone, email string) {
  p.phone = phone
  p.email = email
}

func (p* Participant)SetTshirt(t Tshirt) {
  p.extras.tshirt = t
}

func (p* Participant)SetBeding(b bool) {
  p.extras.beding = b
}

func (p* Participant)SetCadre(c bool) {
  p.extras.cadre = c
}

// Getters

func (p Participant)Info() (ID uint, fn, ln string, age uint, ph, em string) {
  return p.ID, p.firstName, p.lastName, p.age, p.phone, p.email
}
func (p Participant)Tshirt() Tshirt {
  return p.extras.tshirt
}

func (p Participant)Extras() Extras {
  return p.extras
}

func (p Participant)Cadre() bool {
  return p.extras.cadre
}

func (p Participant)Beding() bool {
  return p.extras.beding
}

func (p Participant) Visits() []Visit {
  return p.visits
}

