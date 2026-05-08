import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ApiService, ContactRequest } from '../../services/api.service';

@Component({
  selector: 'app-contact',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.css']
})
export class ContactComponent {
  formData: ContactRequest = {
    name: '',
    email: '',
    subject: '',
    message: ''
  };
  
  status: 'idle' | 'loading' | 'success' | 'error' = 'idle';
  statusMessage = '';

  constructor(private apiService: ApiService) {}

  onSubmit() {
    if (!this.formData.name || !this.formData.email || !this.formData.subject || !this.formData.message) {
      this.status = 'error';
      this.statusMessage = 'Please fill out all fields.';
      return;
    }

    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(this.formData.email)) {
      this.status = 'error';
      this.statusMessage = 'Please enter a valid email address.';
      return;
    }

    this.status = 'loading';
    this.apiService.sendContactMessage(this.formData).subscribe({
      next: (res) => {
        this.status = 'success';
        this.statusMessage = res.message || 'Message sent successfully!';
        this.formData = { name: '', email: '', subject: '', message: '' }; // reset
      },
      error: (err) => {
        this.status = 'error';
        this.statusMessage = err.error?.message || 'Something went wrong. Please try again later.';
        console.error(err);
      }
    });
  }
}
