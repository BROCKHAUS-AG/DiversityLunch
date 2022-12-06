export class HttpError extends Error {
    constructor(private _statusCode : number, private _message? : string) {
        super();
    }

    get statusCode(): number {
        return this._statusCode;
    }

    get message() : string {
        return this._message || this.createErrorMessage();
    }

    private createErrorMessage() {
        switch (this.statusCode) {
            // TODO Alle Statuscodes hinzufügen
            case 404: return 'Not found';
            default: return '';
        }
    }
}
