// javascript is synchronous. : 정해진 순서대로 코드가 실행
// Execute the code block by orger (in order) after hoistin
// hoisting : var function declaration이 맨 위로 올라간다.
// console.log('1');
// console.log('2');
// console.log('3');

// asynchronous 비동기는 언제 코드가 실행될 지 예측할 수 없다.
console.log('1');
setTimeout(() => {console.log('2');}, 1000); //브라우저에서 제공하는 API
console.log('3');

//Synchronous callback-자동으로 호출되는 메서드
function printImmediaely(print){
    print()
}
printImmediaely(() => console.log('hello'));
 
//Asynchronous callback
function printWithDelay(print, timeout){
    setTimeout(print,timeout);
}
printWithDelay(() => console.log('async callback'),2000);