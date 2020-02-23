import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-passwords',
  templateUrl: './passwords.component.html',
  styleUrls: ['./passwords.component.css']
})
export class PasswordsComponent implements OnInit {

  password: string = '';

  constructor() { }

  ngOnInit(): void {
  }

  passwordStatus() {
    const passwordlength = this.password.length;
    if (passwordlength < 8) {
      return 'Password length not long enough. Should be at least 8 characters long.';
    }
    const lengthMessage = 'Password length: ' + passwordlength + '. ';
    if (passwordlength > 7 && passwordlength < 12 && (!this.containsLowercase() || !this.containsUppercase() || !this.containsNumbers() || !this.containsSpecialCharacters())) {
      return lengthMessage + 'Passwords of this length should have lowercase letters, uppercase letters, numbers and symbols.';
    }
    if ((passwordlength > 11 && passwordlength < 16) && (!this.containsLowercase() || !this.containsUppercase() || !this.containsNumbers())) {
      return lengthMessage + 'Passwords of this length should have lowercase letters, uppercase letters and numbers.';
    }
    if ((passwordlength > 15 && passwordlength < 20) && (!this.containsLowercase() || !this.containsUppercase())) {
      return lengthMessage + 'Passwords of this length should have lowercase letters and uppercase letters.';;
    }
    return 'Password meets requirements ;)';
  }

  containsLowercase(): boolean {
    return (/[a-z]/.test(this.password));
  }

  containsUppercase(): boolean {
    return (/[A-Z]/.test(this.password));
  }

  containsNumbers(): boolean {
    return (/[0-9]/.test(this.password));
  }

  containsSpecialCharacters(): boolean {
    return (/\W/.test(this.password));
  }

}
