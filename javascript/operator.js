console.log('my' + 'dog');
console.log('1' + 2); // 2는 문자열로 변환
console.log(`string literals: 1 + 2 = ${1 + 2}`)

console.log(1 + 1);
console.log(1 - 1);
console.log(1 / 1);
console.log(1 * 1);
console.log(5 % 2); //나머지
console.log(2 ** 3); //거듭제곱

let counter = 2;
const preIncrement = ++counter; //counter값이 먼저 증가된후  할당된다.
console.log(`preIncrement: ${preIncrement}, counter:${counter}`)

const postIncrement = counter++; //할당먼저 된후  counter값이 1증가한다.
console.log(`preIncrement: ${preIncrement}, counter:${counter}`)

//연산 후 할당연산
let x = 3;
let y = 6;
x += y; // x = x + y;
x -= y;
x /= y;
x *= y;

//비교연산
console.log(10 < 6);
console.log(10 <= 6);
console.log(10 > 6);
console.log(10 >= 6);

//논리 연산  ||(or), &&(and), !(not)
const value1 = true;
const value2 = 4 < 2;

// or  연산 (첫번째 값이 true이면 뒤에 두개는 확인하지 않는다 check()함수 실행 안함)
console.log(`or: ${value1 || value2 || check()}`);

// and 연산 (check() 처럼 내용이 긴 경우 마지막 비교 부분에 넣는것이 좋다.)
console.log(`and: ${value1 && value2 && check()}`);

//객체가 널 인지 확인후 널이 안닐경우만 객체의 속성이나 함수를 호출한다.
// if(nullableObject != null){
//     nullableObject.something;
// }

function check() {
    for (let i = 0; i < 10; i++) {
        console.log('wating ?');
    }
    return true;
}

// !(not)
console.log(!value1)


//Equality (==, ===)
const stringfive = '5';
const numberfive = 5;

//== loose equality, with type conversion (타입변환을 사용한 약한 비교)
console.log(stringfive == numberfive);
console.log(stringfive != numberfive);

//=== strict equality, no type convrsion(타입변환을 하지 않는 엄격한 비교)
console.log(stringfive === numberfive);
console.log(stringfive !== numberfive);

// object equality by reference
const js1 = { name: 'js' };
const js2 = { name: 'js' };
const js3 = js1;
console.log(js1 == js2);
console.log(js1 === js2)
console.log(js1 === js3)

//equality 확인 
console.log(0 == false);
console.log(0 === false);
console.log('' == false);
console.log('' === false);
console.log(null == undefined);
console.log(null === undefined);
