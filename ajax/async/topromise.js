class UserStorage {
    loginUser(id,password){
        return new Promise((resolve, reject) => {
            setTimeout(()=>{
                if (
                    (id === 'study' && password === 'aistudy') ||
                    (id === 'coder' && password === 'academy')
                ) {
                    resolve(id);
                } else {
                    reject(new Error('not found'))
                }
            }, 2000);

        });  
    }
    getRoles(user) {
        return new Promise((resolve, reject) => {
            setTimeout(() => {
                if(user === 'study' ){
                    resolve({ name : 'study', role : 'admin'});
                } else {
                    reject(new Error('no access'))
                }
            },1000);
        });

    }     

}

const userStorage = new UserStorage();
const id = prompt('enter your id');
const password = prompt('enter your password');
userStorage
    .loginUser(id, password) //로그인 성공하면 id 전달
    .then(userStorage.getRoles) //id의 역할을 전달
    .then(user => alert(`Hello ${user.name}, you have a ${user.role} role`)) //역할 확인
    .catch(console.log); //문제발생시 오류출력