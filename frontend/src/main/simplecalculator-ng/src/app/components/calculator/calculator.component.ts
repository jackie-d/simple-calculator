import { Component, OnInit } from '@angular/core';

import { ApiService } from '../../services/api.service';

import { Equation } from '../../models/equation';

import { faCaretSquareLeft, faCaretSquareRight, faEraser, faSpinner } from '@fortawesome/free-solid-svg-icons';

export const HISTORY_PAGE_SIZE = 10;

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
    private currentHistoryShown = -1;
    private currentHistoryPageLoaded = null;

    constructor(
        private api: ApiService
    ) {}

    ngOnInit(): void {
        this.loadHistoryNextPage();
    }

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
        const signSymbol = SignSymbols[this.sign];
        // await new Promise( resolve => setTimeout(resolve, 500) );
        try {
            const equationObj: any = await this.api.solveEquation(this.number1, this.number2, signSymbol).toPromise();
            const equation: Equation = equationObj;
            console.log(equation);
            this.state = State.Result;
            this.showResult(equation);
        } catch(error) {
            alert('API Server error');
            this.reset();
        }
    }

    private showResult(equation: Equation) {
        this.screenValue = equation.result + '';
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

    // Equation history and pagination handling
    
    public async showPreviousFromHistory() {
        if ( !this.isPreviousFromHistoryEnabled() ) {
            return;
        }
        if ( this.currentHistoryShown + 1 > this.currentHistoryPageLoaded * HISTORY_PAGE_SIZE - 1 ) {
            this.state = State.Loading;
            await this.loadHistoryNextPage();
        }
        this.state = State.Result;
        this.currentHistoryShown++;
        this.showHistoryEquation();
    }

    public showNextFromHistory() {
        if ( !this.isNextFromHistoryEnabled() ) {
                return;
        }
        this.state = State.Result;
        this.currentHistoryShown--;
        this.showHistoryEquation();
    }

    private showHistoryEquation() {
        const equation = this.history[this.currentHistoryShown];
        this.upperScreenValue = equation.number1 + ' ' + equation.sign + ' ' + equation.number2;
        this.screenValue = equation.result;
    }

    public isNextFromHistoryEnabled() {
        return this.history && this.history.length > 0 && 
            this.currentHistoryShown > 0;
    }

    public isPreviousFromHistoryEnabled() {
        return this.history && this.history.length > 0 &&
            this.currentHistoryShown < this.history.length - 1;
    }

    private loadHistoryNextPage() : Promise<boolean> {
        const pageToLoad = !this.currentHistoryPageLoaded ? 0 : this.currentHistoryPageLoaded + 1;
        this.currentHistoryPageLoaded = pageToLoad;
        return this.api.getEquationsHistory(pageToLoad).toPromise()
            .then((historySlice: Equation[] )=> {
                if ( !historySlice ) {
                    return false;
                }
                for ( let i = 0; i < historySlice.length; i++ ) {
                    const elPosition = (pageToLoad * HISTORY_PAGE_SIZE) + i;
                    this.history[elPosition] = historySlice[i];
                }
                return true;
            });
    }

}

export enum State {
    FirstNumber,
    SecondNumber,
    Result,
    Loading
};

export const SignSymbols = {
    '+': '+',
    '-': '-',
    'x': 'x',
    '/': ':'
};