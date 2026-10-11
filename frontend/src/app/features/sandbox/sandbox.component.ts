import { Component } from '@angular/core';

interface SandboxAnswer {
  key: string;
  label: string;
  percent: number;
  correct: boolean;
}

@Component({
  selector: 'app-sandbox',
  standalone: true,
  templateUrl: './sandbox.component.html'
})
export class SandboxComponent {
  readonly answers: SandboxAnswer[] = [
    { key: 'A', label: 'Sydney', percent: 21, correct: false },
    { key: 'B', label: 'Melbourne', percent: 12, correct: false },
    { key: 'C', label: 'Canberra', percent: 58, correct: true },
    { key: 'D', label: 'Perth, capitale de l\'Australie-Occidentale', percent: 9, correct: false }
  ];
}
