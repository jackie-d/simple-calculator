import { Component, OnInit } from '@angular/core';

import { faHistory, faEraser } from '@fortawesome/free-solid-svg-icons';

@Component({
    selector: 'app-calculator',
    templateUrl: './calculator.component.html',
    styleUrls: ['./calculator.component.scss'],
    host: {
        '(document:keypress)': 'handleKeyboardEvents($event)'
    }
})
export class CalculatorComponent implements OnInit {

    screenValue: string = '';
    upperScreenValue: string = '=';

    state: State = State.FirstNumber;

    number1 = null;
    number2 = null;
    sign = null;

    faHistory = faHistory;
    faEraser = faEraser;


    constructor() { }

    ngOnInit(): void {}

    digitNumber(number: string){
        if ( this.state == State.Result ) {
            this.reset();
        } 
        this.screenValue += number;
    }

    digitSign(sign: string) {
        if ( this.screenValue == '' ) {
            return;
        }
        this.number1 = parseInt(this.screenValue);
        this.screenValue = '';
        this.sign = sign;
    }

    getResult() {
        if ( this.screenValue == '' ) {
            return;
        }
        this.number2 = parseInt(this.screenValue);
        this.screenValue = '';
        this.elaborateResult();
    }

    reset() {
        this.state = State.FirstNumber;
        this.screenValue = '';
        this.upperScreenValue = '';
        this.number1 = null;
        this.number2 = null;
        this.sign = null;
    }

    isSignButtonsEnabled() {
        return this.state == State.FirstNumber;// && this.state != State.Loading;
    }

    isResultButtonEnabled() {
        return this.state == State.SecondNumber;// && this.state != State.Loading;
    }

    async elaborateResult() {
        // mock
        this.state = State.Loading;
        await new Promise( resolve => setTimeout(resolve, 500) );
        this.state = State.Result;
        this.showResult();
    }

    showResult() {
        
    }


    handleKeyboardEvents(keyPress: KeyboardEvent) {
        let key = keyPress.key;
        switch (key) {
            case '0': this.digitNumber(key); break;
            case '1': this.digitNumber(key); break;
            case '2': this.digitNumber(key); break;
            case '3': this.digitNumber(key); break;
            case '4': this.digitNumber(key); break;
            case '5': this.digitNumber(key); break;
            case '6': this.digitNumber(key); break;
            case '7': this.digitNumber(key); break;
            case '8': this.digitNumber(key); break;
            case '9': this.digitNumber(key); break;
            case '+': this.digitSign(key); break;
            case '-': this.digitSign(key); break;
            case '*': this.digitSign(key); break;
            case '/': this.digitSign(key); break;
            case '=': this.getResult(); break;
            case 'Enter': this.getResult(); break;
        }
    }

    
    showPreviousFromHistory() {

    }

    showNextFromHistory() {

    }

}


export enum State {
    FirstNumber,
    SecondNumber,
    Result,
    Loading
};