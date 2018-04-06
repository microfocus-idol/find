start
    = _ expr:ShapeExpression _ { return expr }

ShapeExpression
    = PolygonExpression
    / PointExpression

PointExpression
    = 'POINT' _ '(' _ pos:Point _  ')' { return { type: 'POINT', point: pos } }

PolygonExpression
    = 'POLYGON' _ '(' _ first:LineExpression _ others:( ',' _ LineExpression _ )* ')' {  return { type: 'POLYGON', polygon: [first].concat(others.map(function(a){return a[2]})) } }

LineExpression
    = '(' _ first:Point _ others:( ',' _ Point _ )* ')' {
        return [first].concat(others.map(function(a) { return a[2] }))
    }

// Note: we return the numbers in (lat, lon) format; the reverse of WKT, for compatibility with Leaflet's input format
Point
    = lon:number __ lat:number { return [lat, lon] }

number "number"
  = minus? int frac? exp? { return parseFloat(text()); }

decimal_point
  = "."

digit1_9
  = [1-9]

e
  = [eE]

exp
  = e (minus / plus)? DIGIT+

frac
  = decimal_point DIGIT+

int
  = zero / (digit1_9 DIGIT*)

minus
  = "-"

plus
  = "+"

zero
  = "0"

DIGIT  = [0-9]

_
    = ' '*

__
    = ' '+
