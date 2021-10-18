// 1 Object -> JSON
// stringify(obj)
let json = JSON.stringify(true);
console.log(`value : ${json}, type : ${typeof json}`);

json = JSON.stringify(['apple','banana']);
console.log(`value : ${json}, type : ${typeof json}`);

const rabbit = {
    name : 'tori',
    color : 'white',
    birthdate : new Date(),
    jump:() => {
        console.log(`${name} can jump!`);
    },
};

json = JSON.stringify(rabbit);
console.log(`value : ${json}, type : ${typeof json}`);

json = JSON.stringify(rabbit, ['name','color']);
console.log(`value : ${json}, type : ${typeof json}`);

json = JSON.stringify(rabbit, (key, value) => {
    console.log(`ket: ${key}, value: ${value}`);
    return key === 'name'? 'rab':value;
});
console.log(`value : ${json}, type : ${typeof json}`);

// 2 JSON -> Object
// parse(json)

console.clear();
json = JSON.stringify(rabbit);
const obj = JSON.parse(json);
console.log(obj);
rabbit.jump();

console.log(rabbit.birthdate.getDate());

const obj2 = JSON.parse(json, (key, value) => {
    console.log(`key: ${key}, value: ${value}`);
    return key === 'birthdate' ? new Date(value) : value;
});
console.log(obj2.birthdate.getDate()); 