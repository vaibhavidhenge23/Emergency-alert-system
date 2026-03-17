# Emergency Alert System

A robust, real-time coordination platform designed for critical incident management, fire safety monitoring, and emergency response.

![License](https://img.shields.io/github/license/vaibhavidhenge23/Emergency-alert-system?style=flat-square)
![Stars](https://img.shields.io/github/stars/vaibhavidhenge23/Emergency-alert-system?style=flat-square)
![Issues](https://img.shields.io/github/issues/vaibhavidhenge23/Emergency-alert-system?style=flat-square)
![Pull Requests](https://img.shields.io/github/issues-pr/vaibhavidhenge23/Emergency-alert-system?style=flat-square)
![Last Commit](https://img.shields.io/github/last-commit/vaibhavidhenge23/Emergency-alert-system?style=flat-square)
![Repo Size](https://img.shields.io/github/repo-size/vaibhavidhenge23/Emergency-alert-system?style=flat-square)

---

## 🚀 Overview

In emergency situations, every second counts. This Emergency Alert System provides a centralized hub for managing fire safety, medical alerts, and security incidents. It replaces manual logging with a digital dashboard that offers role-based visibility, ensuring that the right personnel get the right information instantly. The UI is utility-driven and "lowkey," prioritizing rapid information access over visual clutter.

## ✨ Features

- **Instant Alert Triggering:** High-priority interface for rapid incident reporting and hazard identification.
- **Resource Management:** Track the availability and status of emergency equipment (e.g., fire extinguishers, medical kits).
- **Role-Based Dashboards:** Distinct views for Administrators, Emergency Responders, and Safety Officers.
- **Incident Logging:** Automated timestamping and historical data tracking for post-incident audits.
- **Fire Safety Focus:** Specialized modules for monitoring fire-related hazards and coordinating evacuations.

## 🛠 Tech Stack

| Category | Technology |
| :--- | :--- |
| **Frontend Framework** | React.js |
| **Language** | TypeScript |
| **Styling** | Tailwind CSS / CSS Modules |
| **State Management** | React Hooks (Context API) |
| **Icons** | Lucide React |

## 🏗 Architecture

The application follows a secure, state-driven architecture. Alerts triggered at the user level are propagated through a centralized management layer that determines visibility and priority based on user roles.

```mermaid
graph TD
    A[User/Sensor Input] --> B[Alert Trigger Logic]
    B --> C{Role-Based Filter}
    C --> D[Admin Dashboard - Full Control]
    C --> E[Responder View - Actionable Tasks]
    D --> F[Persistence/Logging]
    E --> F
📂 Project StructurePlaintext├── src/
│   ├── components/      # UI components (AlertCard, StatusBadge, Sidebar)
│   ├── pages/           # Dashboard views (Overview, FireSafety, Resources)
│   ├── context/         # Global state for Alerts and User Roles
│   ├── utils/           # Helper functions for priority logic and formatting
│   ├── App.tsx          # Main routing and layout
│   └── index.tsx        # Entry point
├── public/              # System assets and safety icons
└── tailwind.config.js   # Custom theme for high-visibility alerts
⚙️ InstallationTo set up the emergency system locally:Clone the repository:Bashgit clone [https://github.com/vaibhavidhenge23/Emergency-alert-system.git](https://github.com/vaibhavidhenge23/Emergency-alert-system.git)
cd Emergency-alert-system
Install dependencies:Bashnpm install
Start the application:Bashnpm start
🖥 UsageAfter launching the application at http://localhost:3000:Trigger Alert: Use the primary action button to report a new incident (Fire, Medical, or Security).Update Status: Mark resources as "Deployed" or "Available" in the Logistics tab.Audit Logs: Navigate to the History section to review past incidents and response times.🌐 ConfigurationEnsure the following environment variables are set for production deployments:VariableDescriptionREACT_APP_EMERGENCY_CONTACTPrimary contact number for emergency servicesREACT_APP_REGION_CODELocalization code for regional safety protocols📸 Demo(Add screenshots of the Emergency Dashboard and Alert Trigger screen here)🤝 ContributingThis is a safety-oriented project. For major changes, please open an issue first to discuss your proposal.Fork the Project.Create your Feature Branch (git checkout -b feature/NewAlertSystem).Commit your changes.Push to the Branch.Open a Pull Request.🗺 Roadmap[ ] Real-time SMS/Push notifications via Twilio integration.[ ] Live geolocation tracking for responders on a map view.[ ] Offline-first support for areas with poor connectivity.[ ] IoT integration for automated fire sensor triggers.📄 LicenseThis project is licensed under the MIT License.d
