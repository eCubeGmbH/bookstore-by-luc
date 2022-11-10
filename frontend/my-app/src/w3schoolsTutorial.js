class Car{
    constructor(name) {
        this.brand = name;
    }

    present() {
        return 'I have a ' + this.brand;
    }
}

class Model extends Car {
    constructor(name, mod) {
        super(name);
        this.model = mod;
    }
    show() {
        return this.present() + ' , it is a ' + this.model
    }
}

let x = 5.6;

const mycar = new Model("Ford", "Mustang");
mycar.show();