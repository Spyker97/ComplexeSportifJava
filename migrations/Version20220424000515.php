<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20220424000515 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('CREATE TABLE endroit (id INT AUTO_INCREMENT NOT NULL, region_id_id INT NOT NULL, nom VARCHAR(255) NOT NULL, type VARCHAR(255) DEFAULT NULL, description LONGTEXT NOT NULL, longitude DOUBLE PRECISION NOT NULL, latitude DOUBLE PRECISION NOT NULL, numero INT DEFAULT NULL, image VARCHAR(255) DEFAULT NULL, INDEX IDX_7B44825AC7209D4F (region_id_id), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE region (id INT AUTO_INCREMENT NOT NULL, nom VARCHAR(255) NOT NULL, description LONGTEXT DEFAULT NULL, photo VARCHAR(255) DEFAULT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('ALTER TABLE endroit ADD CONSTRAINT FK_7B44825AC7209D4F FOREIGN KEY (region_id_id) REFERENCES region (id)');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE endroit DROP FOREIGN KEY FK_7B44825AC7209D4F');
        $this->addSql('DROP TABLE endroit');
        $this->addSql('DROP TABLE region');
    }
}
