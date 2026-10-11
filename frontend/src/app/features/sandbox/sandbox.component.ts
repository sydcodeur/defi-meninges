import { Component } from '@angular/core';

interface SandboxAnswer {
  key: string;
  label: string;
  correct: boolean;
}

@Component({
  selector: 'app-sandbox',
  standalone: true,
  templateUrl: './sandbox.component.html'
})
export class SandboxComponent {
  readonly winner = 'Jonathan';

  readonly answers: SandboxAnswer[] = [
    { key: 'A', label: 'Sydney', correct: false },
    { key: 'B', label: 'Melbourne', correct: false },
    { key: 'C', label: 'Canberra', correct: true },
    { key: 'D', label: 'Perth, capitale de l\'Australie-Occidentale', correct: false }
  ];
}
