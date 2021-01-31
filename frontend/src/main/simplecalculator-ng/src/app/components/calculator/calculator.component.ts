import { Component, OnInit } from '@angular/core';

import { faCaretSquareLeft, faCaretSquareRight, faEraser, faSpinner } from '@fortawesome/free-solid-svg-icons';

@Component({
    selector: 'app-calculator',
    templateUrl: './calculator.component.html',
    styleUrls: ['./calculator.component.scss'],
    host: {
        '(document:keypress)': 'handleKeyboardEvents($event)'
    }
})
export class CalculatorComponent implements OnInit {

    public screenValue: string = '';
    public upperScreenValue: string = '';

    private state: State = State.FirstNumber;

    private number1 = null;
    private number2 = null;
    private sign = null;

    public faCaretSquareLeft = faCaretSquareLeft;
    public faCaretSquareRight = faCaretSquareRight;
    public faEraser = faEraser;
    public faSpinner = faSpinner;

    private history = [];
    private currentHistoryShown = null;

    constructor() { }

    ngOnInit(): void {}

    public digitNumber(number: string){
        if ( this.state == State.Result ) {
            this.reset();
        } 
        this.screenValue += number;
    }

    public digitSign(sign: string) {
        if ( this.screenValue == '' ) {
            return;
        }
        this.number1 = parseInt(this.screenValue);
        this.upperScreenValue = this.number1 + ' ' + sign;
        this.screenValue = '';
        this.sign = sign;
        this.state = State.SecondNumber;
    }

    public getResult() {
        if ( this.screenValue == '' ) {
            return;
        }
        this.number2 = parseInt(this.screenValue);
        this.upperScreenValue += ' ' + this.number2;
        this.screenValue = '';
        this.elaborateResult();
    }

    public reset() {
        this.state = State.FirstNumber;
        this.screenValue = '';
        this.upperScreenValue = '';
        this.number1 = null;
        this.number2 = null;
        this.sign = null;
    }

    public isSignButtonsEnabled() {
        return this.state == State.FirstNumber;// && this.state != State.Loading;
    }

    public isResultButtonEnabled() {
        return this.state == State.SecondNumber;// && this.state != State.Loading;
    }

    public isLoadingVisible() {
        return this.state == State.Loading;
    }

    private async elaborateResult() {
        // mock
        this.state = State.Loading;
        await new Promise( resolve => setTimeout(resolve, 500) );
        this.state = State.Result;
        this.showResult();
    }

    private showResult() {
        this.screenValue = '<solution>';
    }


    public handleKeyboardEvents(keyPress: KeyboardEvent) {
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

    
    public showPreviousFromHistory() {
        // show prev history
    }

    public showNextFromHistory() {
        // show next history
    }

}

export enum State {
    FirstNumber,
    SecondNumber,
    Result,
    Loading
};