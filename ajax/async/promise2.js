//3. Promise chaining
const fetchNumber = new Promise((resolve, reject) => {
    setTimeout(() => resolve(1), 1000);
});
fetchNumber
    .then(num => num * 2)
    .then(num => num * 3)
    .then(num => {
        return new Promise((resolve, reject) => {
            setTimeout(() => resolve(num - 1), 1000);
        });
    })
    .then(num => console.log(num));
 
//4. Error Handling
const getHen = () =>
    new Promise((resolve, reject) => {
        setTimeout(() => resolve('🐓'),1000);
    });
 
const getEgg = hen =>
    new Promise((resolve, reject) => {
       setTimeout(() => resolve(`${hen} => 🥚`),1000);
       //setTimeout(() => reject(new Error(`error! ${hen} => ?`)),1000);
    });
 
const cook = egg =>
    new Promise((resolve, reject) => {
        setTimeout(() => resolve(`${egg} => 🍳`),1000);
    });
 
getHen() 
    .then(hen => getEgg(hen))
    .then(egg => cook(egg))
    .then(meal => console.log(meal));
// 받아온 값 하나를 => 다음 함수에 파라메터로 보낼때 간단히 표현방법.
// 32~35줄 간단히
// getHen() 
//     .then(getEgg)
//     .then(cook)
//     .then(console.log)  
//     .catch(console.log); //24라인 에러 처리, 오류난 부분에서 catch부분으로 건너띈다.  
 
// 중간에 오류처리하는 방법, 계란을 받아오다가 오류가 나면 다른재료로 대체한다.
// getHen() 
//     .then(getEgg) 
//     .catch(error => {
//         return '?';
//     })
//     .then(cook) 
//     .then(console.log)
//     .catch(console.log);