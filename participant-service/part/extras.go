package part


type Tshirt struct {
  size  string
  color string
  typ   string
}

type Extras struct {
  tax     bool
  beding  bool
  cadre   bool
  tshirt  Tshirt
}

func NewTshirt(size, color, typ string) Tshirt {
  return Tshirt{size, color, typ}
}

func NewExtras(tax, beding, cadre bool, tshirt Tshirt) Extras {
  return Extras{tax, beding, cadre, tshirt}
}
