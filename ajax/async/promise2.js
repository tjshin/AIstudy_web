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
        setTimeout(() => resolve('π'),1000);
    });
 
const getEgg = hen =>
    new Promise((resolve, reject) => {
       setTimeout(() => resolve(`${hen} => π₯`),1000);
       //setTimeout(() => reject(new Error(`error! ${hen} => ?`)),1000);
    });
 
const cook = egg =>
    new Promise((resolve, reject) => {
        setTimeout(() => resolve(`${egg} => π³`),1000);
    });
 
getHen() 
    .then(hen => getEgg(hen))
    .then(egg => cook(egg))
    .then(meal => console.log(meal));
// λ°μμ¨ κ° νλλ₯Ό => λ€μ ν¨μμ νλΌλ©ν°λ‘ λ³΄λΌλ κ°λ¨ν ννλ°©λ².
// 32~35μ€ κ°λ¨ν
// getHen() 
//     .then(getEgg)
//     .then(cook)
//     .then(console.log)  
//     .catch(console.log); //24λΌμΈ μλ¬ μ²λ¦¬, μ€λ₯λ λΆλΆμμ catchλΆλΆμΌλ‘ κ±΄λλλ€.  
 
// μ€κ°μ μ€λ₯μ²λ¦¬νλ λ°©λ², κ³λμ λ°μμ€λ€κ° μ€λ₯κ° λλ©΄ λ€λ₯Έμ¬λ£λ‘ λμ²΄νλ€.
// getHen() 
//     .then(getEgg) 
//     .catch(error => {
//         return '?';
//     })
//     .then(cook) 
//     .then(console.log)
//     .catch(console.log);