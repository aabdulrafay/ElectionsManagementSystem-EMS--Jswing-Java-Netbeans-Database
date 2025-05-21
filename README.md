# 🗳️ Elections Management System (EMS)

The **Elections Management System (EMS)** is a robust Java-based desktop application built using **NetBeans IDE**, **JSwing** for GUI, and **MySQL** for persistent data storage. This system is designed to offer a secure and efficient platform for managing elections. The project is licensed under the **MIT License**.

---

## 👨‍👩‍👦 Group Members

| Name         | SAP ID |
| ------------ | ------ |
| Abdul Rafay  | 54689  |
| Hassan Zahid | 54481  |
| Abdul Rafay  | 53325  |

> ℹ️ If the second "Abdul Rafay" is a typo or should be a different name, feel free to correct it.

---

## 🚀 Project Overview

EMS simplifies and secures the electoral process with support for two primary roles: **Admin** and **Voter**. Its structure ensures reliable, transparent, and efficient management of all election activities through a user-friendly interface.

---

## 🌟 Key Features

### 🔑 **Admin Panel**

**Default Login Credentials:**

* **Username**: `admin`
* **Password**: `admin`

The Admin has full access to all modules and can perform the following tasks:

#### 🧑‍🤝‍🧑 Voter Management

* **Add Voter**: Register voters with CNIC and constituency.
* **Remove Voter**: Delete voters by constituency.
* **Update Voter**: Modify voter records.

#### 🎯 Candidate Management

* **Add Candidate**: Add candidates by party and constituency.
* **Remove Candidate**: Remove candidates.
* **Update Candidate**: Edit candidate information.

#### 🗺️ Constituency Management

* **Add Constituency**: Create new NA or PA constituencies.
* **Remove Constituency**: Delete existing ones.

#### 📰 Election News

* **Post News**: Share news/announcements with all voters.

#### 🕹️ Election Control

* **Start/End Elections**: Control the election schedule.
* **Validation Check**: Ensure each constituency has at least one candidate before starting.

#### 📊 Result Management

* **View Results**: Display winners, losers, and tie information.
* **Reports**:

  * **Short Report**: Constituency-wise winners.
  * **Detailed Report**: Full vote breakdown.

---

### 👥 **Voter Panel**

Voters interact with the system using a clean, intuitive interface:

#### 🗳️ Voting

* **Cast Vote**: Vote in NA and PA elections once authenticated.

#### 📰 Election News

* **View News**: Stay updated with admin-posted election news.

---

## 🛠️ Implementation Details

### 🔧 Technology Stack

* **Programming Language**: Java
* **GUI**: JSwing
* **IDE**: NetBeans
* **Database**: **MySQL** (used to store voter, candidate, and result data)

### 🔐 Security Features

* Each CNIC can cast only one vote.
* Admin access is restricted through secure login.
* SQL database ensures data integrity and persistence.

### 🧑‍💻 UI/UX Design

* User-friendly interface for both Admin and Voter roles.
* Visually structured layouts for seamless navigation.

---

## 🛠️ How to Run

1. **Clone the Repository**

   ```bash
   gh repo clone abdulrafayy06/ElectionsManagementSystem-EMS--Jswing-Java-Netbeans
   ```

2. **Set Up MySQL Database**

   * Import the SQL file provided in the repository to set up your database.
   * Update your database credentials in the Java code if required.

3. **Open in NetBeans**

   * Launch NetBeans IDE.
   * Open the project folder and clean/build the project.

4. **Run the Application**

   * Hit the **Run** button to start EMS and interact with the system.

---

## 📷 Screenshots

### **Splash Screen**

![Splash Screen](https://github.com/user-attachments/assets/333c462c-d414-46d1-b869-c3a6df57d9c9)

### **Home Screen**

![Home Screen](https://github.com/user-attachments/assets/ff8762e1-0642-4188-94f3-b6a13477cce0)

### **Admin Dashboard**

![Admin Panel](https://github.com/user-attachments/assets/09ba4555-f815-495d-9910-2e46d3a68bec)

### **Voter Panel**

![Voter Panel](https://github.com/user-attachments/assets/c013f40b-4a8b-46b8-99ae-2aaab2f5a281)

### **Voter Interface**

![Voter Interface](https://github.com/user-attachments/assets/8a16f3d3-689c-4f21-8769-e02c52003c52)

### [🎥 DEMO VIDEO LINK ON YOUTUBE](https://youtu.be/wXS2iXzymF8?si=KHnCjCsF8sLZNNXn)

---

## 🤝 Contributing

We welcome contributions to improve EMS!
You can fork this repo, report issues, or suggest enhancements via the [issues page](https://github.com/abdulrafayy06/ElectionsManagementSystem-EMS--Jswing-Java-Netbeans/issues).

---

## 📬 Contact

For any queries or collaborations:
**ABDUL RAFAY**
📧 [lifewithabdulrafay@gmail.com](mailto:lifewithabdulrafay@gmail.com)
🔗 [LinkedIn Profile](https://www.linkedin.com/in/aabdulrafay/)
