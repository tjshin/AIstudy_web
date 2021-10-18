'use strict';
//Promise is a JavaScript object for asynchronous operation.
//State : pending -> fulfilled or rejected
//Producer vs Consumer
 
//1. Producer:제공자
// when new Promise is created, the executor runs automatically.
 
const promise = new Promise((resolve, reject)=>{
    //doing some heavy work(network, read files)
    console.log('doing something...');
    setTimeout(() => {
        resolve('study');
        //reject(new Error('no network'));
    }, 2000)
});
 
//2. Consumers : 사용자 , then, catch, finally
promise //
    .then((value)=>{ // value는 promise가 잘 처리되어 resolve호출하고 거기에 전달된 'study' 를 받는다.
        console.log(value);
    })
    .catch(error => { //error는 promise가 처리되다 오류가 발생하여 전될되는 Error객체를 받는다. 
        console.log(error);
    })
    .finally(() => { //무조건 실행되는 곳
        console.log('finally')
    });