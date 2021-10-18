'use strict';
let name = '홍';
const maxNumber = 5;

console.log(name);
console.log(maxNumber);

let a = 12;
let b = 12.1;

console.log(`value: ${a}, type:${typeof a}`);
console.log(`value: ${b}, type:${typeof b}`);

const infinity = 1 / 0;
const negativeInfinity = -1 / 0;
const nAn = 'not a number' / 2;
console.log(infinity);
console.log(negativeInfinity);
console.log(nAn);

const char = 'c';
const brendan = 'brendan';
const greeting = 'hello' + 'brendan';
console.log(`value: ${greeting}, type: ${typeof greeting}`);

const canRead = true;
const test = 3 < 1;
console.log(`value: ${canRead}, type: ${typeof canRead}`);
console.log(`value: ${test}, type: ${typeof test}`);

const nothing = null;
console.log(`value: ${nothing}, type: ${typeof nothing}`);

let x;
console.log(`value: ${x}, type: ${typeof x}`);

const symbol1 = Symbol('id');
const symbol2 = Symbol('id');
console.log(symbol1 === symbol2);
const gsymbol1 = Symbol.for('id');
const gsymbol2 = Symbol.for('id');
console.log(gsymbol1 === gsymbol2);
console.log(`value: ${symbol1.description}, type: ${typeof symbol1}`);

let text = 'hello';
console.log(text.charAt(0));
console.log(`value: ${text}, type: ${typeof text}`);

text = 1;
console.log(`value: ${text}, type: ${typeof text}`);

text = '7' + 5;
console.log(`value: ${text}, type: ${typeof text}`);

const obj = { name: 'obj', age: 20 };
console.log(`name: ${obj.name}, ${obj.age}, type: ${typeof obj}`);