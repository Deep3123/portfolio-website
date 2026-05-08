import { Component, OnInit, OnDestroy } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-hero',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './hero.component.html',
  styleUrls: ['./hero.component.css']
})
export class HeroComponent implements OnInit, OnDestroy {
  roles = [
    'scalable backend systems.',
    'modern full-stack applications.',
    'high-performance REST APIs.',
    'enterprise microservices.'
  ];
  currentRole = '';
  private roleIndex = 0;
  private charIndex = 0;
  private isDeleting = false;
  private typingSpeed = 100;
  private deletingSpeed = 50;
  private pauseDuration = 2000;
  private timeoutId: any;

  ngOnInit() {
    this.typeEffect();
  }

  ngOnDestroy() {
    if (this.timeoutId) {
      clearTimeout(this.timeoutId);
    }
  }

  private typeEffect() {
    const fullText = this.roles[this.roleIndex];

    if (this.isDeleting) {
      this.currentRole = fullText.substring(0, this.charIndex - 1);
      this.charIndex--;
    } else {
      this.currentRole = fullText.substring(0, this.charIndex + 1);
      this.charIndex++;
    }

    let delay = this.isDeleting ? this.deletingSpeed : this.typingSpeed;

    if (!this.isDeleting && this.currentRole === fullText) {
      delay = this.pauseDuration;
      this.isDeleting = true;
    } else if (this.isDeleting && this.currentRole === '') {
      this.isDeleting = false;
      this.roleIndex = (this.roleIndex + 1) % this.roles.length;
      delay = 500;
    }

    this.timeoutId = setTimeout(() => this.typeEffect(), delay);
  }
}
